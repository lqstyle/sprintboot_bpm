package com.kpmg.bpm.service;

import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 菜单和角色service
 * @date 09/06/2018 10:43 上午
 */
public interface MenuRoleService {
    List<Map<String, Object>> getMenuList(Map<String, Object> map);
}
