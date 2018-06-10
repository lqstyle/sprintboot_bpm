package com.kpmg.bpm.service;

import com.kpmg.bpm.vo.UserRoleRelVO;

import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: ${todo}
 * @date 09/06/2018 10:43 上午
 */
public interface UserRoleService {
    List<UserRoleRelVO> getUserRoleListByMap(Map<String, Object> map);

    int insertSelective(UserRoleRelVO record);
}
