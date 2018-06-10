package com.kpmg.bpm.controller;

import com.google.common.base.Preconditions;
import com.kpmg.bpm.service.MenuRoleService;
import com.kpmg.bpm.vo.UserRoleRelVO;
import com.kpmg.bpm.vo.UserVo;
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
 * @description: 登陆控制器
 * @date 07/06/2018 10:13 上午
 */
@RestController
public class IndexController {
    @Autowired
    private MenuRoleService menuRoleService ;

    @RequestMapping("/")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/{ftlName}", method = RequestMethod.GET)
    public ModelAndView showJspHome(HttpSession session, @PathVariable(value = "ftlName") String ftlName,String roleId) {
        ModelAndView model = new ModelAndView();
        UserVo user = (UserVo) session.getAttribute("User");
            Preconditions.checkArgument(user != null, "用户会话超时");
        Preconditions.checkArgument(!user.getLoginName().equals(""), "用户名不能为空");
        Preconditions.checkArgument(!user.getId().equals(""), "用户id不能为空");
        String userName = user.getLoginName();

        Map<String, Object> roleMap = new HashMap<>();
        Preconditions.checkArgument(!roleId.equals(""), "角色id不能为空");
        roleMap.put("roleId", roleId);
        List<Map<String, Object>> menuList = menuRoleService.getMenuList(roleMap);
        String menuUrl = "";
        List<String> menuUrlList = new ArrayList<>();
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
        model.setViewName(ftlName);
        model.addObject("userName", userName);
        return model;
    }
}
