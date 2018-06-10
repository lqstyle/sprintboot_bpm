package com.kpmg.bpm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.kpmg.bpm.common.ResponseEntity;
import com.kpmg.bpm.service.MenuRoleService;
import com.kpmg.bpm.service.RoleService;
import com.kpmg.bpm.service.UserRoleService;
import com.kpmg.bpm.service.UserService;
import com.kpmg.bpm.vo.*;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 用户控制器
 * @date 07/06/2018 10:43 上午
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuRoleService menuRoleService;
    @Autowired
    private RoleService roleService;


    /**
     * 功能描述:校验用户登陆，此处简单通过用户名密码认证
     *
     * @param: [session, request]
     * @return: java.util.Map
     * @author: lucasliang
     * @date: 08/06/2018 2:24 下午
     */
    @RequestMapping("/validateUser")
    public ModelAndView validateUser(HttpSession session, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        Preconditions.checkArgument(!userName.equals(""), "用户名不能为空");
        Preconditions.checkArgument(!userPassword.equals(""), "密码不能为空");
        UserVoCriteria userCriteria = new UserVoCriteria();
        UserVoCriteria.Criteria example = userCriteria.createCriteria();
        example.andLoginNameEqualTo(userName);
        example.andPasswordEqualTo(userPassword);
        List<UserVo> userList = userService.getUserList(userCriteria);
        if (!CollectionUtils.isEmpty(userList)) {
            UserVo user = userList.get(0);
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> roleMap = new HashMap<>();
            List<String> menuUrlList = new ArrayList<>();
            String userId = user.getId();
            String roleId = "";
            map.put("userId", userId);
            /*
            * 通过用户id获取用户所在的角色
            * */
            List<UserRoleRelVO> userRoleRelVOList = userRoleService.getUserRoleListByMap(map);
            if (!CollectionUtils.isEmpty(userRoleRelVOList)) {
                UserRoleRelVO userRoleRelVO = userRoleRelVOList.get(0);
                Preconditions.checkArgument(userRoleRelVO != null, "用户名不能为空");
                Preconditions.checkArgument(!userRoleRelVO.getRoleId().equals(""), "角色id不能为空");
                roleId = userRoleRelVO.getRoleId();
                roleMap.put("roleId", roleId);
                List<Map<String, Object>> menuList = menuRoleService.getMenuList(roleMap);
                String menuUrl = "";
                if (!CollectionUtils.isEmpty(menuList)) {
                    for (Map<String, Object> map1 : menuList) {
                        Preconditions.checkArgument(map1.get("href") != null, "菜单url不能为空");
                        menuUrl = map1.get("href") != null ? map1.get("href").toString() : "";
                        if (!StringUtils.isEmpty(menuUrl)) {
                            menuUrlList.add(menuUrl);
                        }
                    }
                }
                model.addObject("menuUrlList", menuUrlList);
            }
            session.setAttribute("User", user);
            model.addObject("roleId", roleId);
            model.setViewName("mainPage");
        } else {
            model.setViewName("page");
        }
        return model;
    }

    /**
     * 功能描述:  添加用户
     *
     * @param: [user, synToActiviti]
     * @return: int
     * @author: lucasliang
     * @date: 09/06/2018 10:06 上午
     */
    @RequestMapping("/addUser")
    public int saveUser(UserVo user, String createUser, String roleName, boolean synToActiviti) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        Preconditions.checkArgument(!createUser.equals(""), "当前登录人不能为空");
        user.setCreateBy(createUser);
        user.setCreateDate(new Date());
        user.setDelFlag("0");
        /*
        * 保存用户信息
        * */
        int i = userService.saveUser(user);
        /*
        * 保存用户和角色关系表
        * */
        Preconditions.checkArgument(!roleName.equals(""), "用户名不能为空");
        RoleVoCriteria roleVoCriteria = new RoleVoCriteria();
        RoleVoCriteria.Criteria criteria = roleVoCriteria.createCriteria().andNameEqualTo(roleName);
        List<RoleVo> roleList = roleService.getRoleList(roleVoCriteria);
        Preconditions.checkArgument(roleList.size() > 0, "角色不能为空");
        RoleVo roleVo = roleList.get(0);
        UserRoleRelVO userRoleRelVO = new UserRoleRelVO();
        userRoleRelVO.setUserId(userId);
        userRoleRelVO.setRoleId(roleVo.getId());
        int j = userRoleService.insertSelective(userRoleRelVO);

        if (synToActiviti) {
            List<org.activiti.engine.identity.User> activitiUsers = identityService.createUserQuery().userId(userId).list();
            List<org.activiti.engine.identity.Group> activitiGroupList = (List<Group>) identityService.createGroupQuery().groupId(roleVo.getId()).list();
            if (activitiUsers.size() == 1) {
                //更新用户信息
                org.activiti.engine.identity.User activitiUser = activitiUsers.get(0);
                activitiUser.setFirstName(user.getLoginName());
                activitiUser.setLastName("");
                activitiUser.setPassword(user.getPassword());
                activitiUser.setEmail(user.getEmail());
                identityService.saveUser(activitiUser);
            } else {
                //新增用户信息
                org.activiti.engine.identity.User newUser = identityService.newUser(userId);
                newUser.setFirstName(user.getLoginName());
                newUser.setLastName("");
                newUser.setPassword(user.getPassword());
                newUser.setEmail(user.getEmail());
                identityService.saveUser(newUser);
                //新增组信息
            }
            if (activitiGroupList.size() == 1) {
                //更新组信息
                org.activiti.engine.identity.Group group = activitiGroupList.get(0);
                group.setName(roleName);
            } else {
                Group group = identityService.newGroup(roleVo.getId());
                group.setName(roleName);
                identityService.saveGroup(group);
            }
            //新增角色和用户的关系
            if (activitiUsers.size() == 0 && activitiGroupList.size() == 0) {
                identityService.createMembership(userId, roleVo.getId());
            }
        }
        return i+j;
    }

    @RequestMapping("/updateUser")
    public int updateUser(String userId) {
        Preconditions.checkArgument(!userId.equals(""), "用户id不能为空");
        UserVo user = userService.selectByPrimaryKey(userId);
        user.setDelFlag("1");
        return userService.updateByPrimaryKey(user);
    }


    /**
     * 功能描述: 获取用户
     *
     * @param: [user, synToActiviti]
     * @return: int
     * @author: lucasliang
     * @date: 09/06/2018 10:06 上午
     */
    @RequestMapping(value = "/restGetUsers", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity getUsers(@RequestBody Map<String, Object> params) {
        ResponseEntity responseEntity = new ResponseEntity();
        int page = Integer.parseInt(params.get("page") != null ? params.get("page").toString() : "");
        int rows = Integer.parseInt(params.get("rows") != null ? params.get("rows").toString() : "");
        PageHelper.startPage(page, rows);
        List<UserVo> userVoList = userService.getUserListByMap(params);
        responseEntity.setRows(userVoList);
        // 取总记录数
        PageInfo<UserVo> pageInfo = new PageInfo<>(userVoList);
        responseEntity.setRecords(pageInfo.getTotal());
        responseEntity.setTotal(pageInfo.getPages());
        return responseEntity;
    }

    @RequestMapping(value = "/addUserPage", method = RequestMethod.GET)
    public ModelAndView showJspHome(HttpSession session) {
        ModelAndView model = new ModelAndView();
        UserVo user = (UserVo) session.getAttribute("User");
        Preconditions.checkArgument(user != null, "用户会话超时");
        Preconditions.checkArgument(!user.getLoginName().equals(""), "用户名不能为空");
        Preconditions.checkArgument(!user.getId().equals(""), "用户id不能为空");
        String loginName = user.getLoginName();
        model.setViewName("addUserPage");
        model.addObject("userName", loginName);
        return model;
    }
}
