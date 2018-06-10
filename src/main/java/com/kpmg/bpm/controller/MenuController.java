package com.kpmg.bpm.controller;

import com.google.common.base.Preconditions;
import com.kpmg.bpm.service.MenuRoleService;
import com.kpmg.bpm.service.UserRoleService;
import com.kpmg.bpm.service.UserService;
import com.kpmg.bpm.vo.UserRoleRelVO;
import com.kpmg.bpm.vo.UserVo;
import com.kpmg.bpm.vo.UserVoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 页面显示菜单控制器
 * @date 09/06/2018 9:39 上午
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuRoleService menuRoleService;

    @RequestMapping(value = "/{ftlName}", method = RequestMethod.GET)
    public ModelAndView showJspHome(HttpSession session, @PathVariable(value = "ftlName") String ftlName) {
        ModelAndView model = new ModelAndView();
        UserVo user = (UserVo) session.getAttribute("User");
        Preconditions.checkArgument(user != null, "用户会话超时");
        Preconditions.checkArgument(!user.getLoginName().equals(""), "用户名不能为空");
        Preconditions.checkArgument(!user.getId().equals(""), "用户id不能为空");
        String userName = user.getLoginName();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> roleMap = new HashMap<>();
        List<String> menuUrlList = new ArrayList<>();
        String userId = user.getId();
        map.put("userId", userId);
            /*
            * 通过用户id获取用户所在的角色
            * */
        List<UserRoleRelVO> userRoleRelVOList = userRoleService.getUserRoleListByMap(map);
        if (!CollectionUtils.isEmpty(userRoleRelVOList)) {
            UserRoleRelVO userRoleRelVO = userRoleRelVOList.get(0);
            Preconditions.checkArgument(userRoleRelVO != null, "用户名不能为空");
            Preconditions.checkArgument(!userRoleRelVO.getRoleId().equals(""), "角色id不能为空");
            String roleId = userRoleRelVO.getRoleId();
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
            model.addObject("roleId",roleId);
        }
        model.setViewName(ftlName);
        model.addObject("userName", userName);
        return model;
    }
}
