package com.kpmg.bpm.service.impl;

import com.kpmg.bpm.mapper.UserRoleRelVOMapper;
import com.kpmg.bpm.service.UserRoleService;
import com.kpmg.bpm.vo.UserRoleRelVO;
import com.kpmg.bpm.vo.UserRoleRelVOCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: ${todo}
 * @date 09/06/2018 10:44 上午
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRelVOMapper userRoleRelVOMapper;

    @Override
    public List<UserRoleRelVO> getUserRoleListByMap(Map<String, Object> map) {
        UserRoleRelVOCriteria userRoleRelVOCriteria = new UserRoleRelVOCriteria();
        UserRoleRelVOCriteria.Criteria criteria = userRoleRelVOCriteria.createCriteria();
        String userId = map.get("userId") != null ? map.get("userId").toString() : "";
        criteria.andUserIdEqualTo(userId);
        return userRoleRelVOMapper.selectByExample(userRoleRelVOCriteria);
    }

    @Override
    public int insertSelective(UserRoleRelVO record) {
        return userRoleRelVOMapper.insertSelective(record);
    }
}
