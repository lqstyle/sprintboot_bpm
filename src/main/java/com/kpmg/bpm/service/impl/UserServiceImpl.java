package com.kpmg.bpm.service.impl;

import com.kpmg.bpm.mapper.UserVoMapper;
import com.kpmg.bpm.service.UserService;
import com.kpmg.bpm.vo.UserVo;
import com.kpmg.bpm.vo.UserVoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 用户服务层实现
 * @date 07/06/2018 10:46 上午
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserVoMapper userVoMapper;

    /**
     * 功能描述:  获取用户list
     *
     * @param: [userCriteria]
     * @return: java.util.List<demo.bpm.bpmvacationflow.vo.User>
     * @author: lucasliang
     * @date: 08/06/2018 2:27 下午
     */
    @Override
    public List<UserVo> getUserList(UserVoCriteria userCriteria) {
        return userVoMapper.selectByExample(userCriteria);
    }

    /**
     * 功能描述: 保存用户
     *
     * @param: [user]
     * @return: int
     * @author: lucasliang
     * @date: 09/06/2018 9:44 上午
     */
    @Override
    public int saveUser(UserVo user) {
        return userVoMapper.insert(user);
    }

    @Override
    public List<UserVo> getUserListByMap(Map<String, Object> map) {
        String loginName = map.get("loginName") != null ? map.get("loginName").toString() : "";
        String employeeNo = map.get("employeeNo") != null ? map.get("employeeNo").toString() : "";
        UserVoCriteria userVoCriteria = new UserVoCriteria();
        UserVoCriteria.Criteria criteria = userVoCriteria.createCriteria();
        if(!StringUtils.isEmpty(loginName)){
            criteria.andLoginNameLike(loginName);
        }
        if(!StringUtils.isEmpty(employeeNo)){
            criteria.andEmployeeNoLike(employeeNo);
        }
        return userVoMapper.selectByExample(userVoCriteria);
    }

    @Override
    public UserVo selectByPrimaryKey(String id) {
        return userVoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UserVo record) {
        return userVoMapper.updateByPrimaryKey(record);
    }

}
