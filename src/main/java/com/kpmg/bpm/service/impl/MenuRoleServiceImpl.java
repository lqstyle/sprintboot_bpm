package com.kpmg.bpm.service.impl;

import com.kpmg.bpm.mapper.MenuRoleRelVoMapper;
import com.kpmg.bpm.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 菜单和角色关联持久层
 * @date 09/06/2018 10:43 上午
 */
@Service
public class MenuRoleServiceImpl implements MenuRoleService {
    @Autowired
    private MenuRoleRelVoMapper menuRoleRelVoMapper;

    @Override
    public List<Map<String, Object>> getMenuList(Map<String, Object> map) {
        return menuRoleRelVoMapper.getMenuList(map);
    }
}
