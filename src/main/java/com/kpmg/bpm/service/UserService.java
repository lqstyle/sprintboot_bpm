package com.kpmg.bpm.service;

import com.kpmg.bpm.vo.UserVo;
import com.kpmg.bpm.vo.UserVoCriteria;

import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 用户服务层
 * @date 07/06/2018 10:45 上午
 */
public interface UserService {
    /**
     * 功能描述:  获取用户list
     *
     * @param: [userCriteria]
     * @return: java.util.List<demo.bpm.bpmvacationflow.vo.User>
     * @author: lucasliang
     * @date: 08/06/2018 2:27 下午
     */
    List<UserVo> getUserList(UserVoCriteria userVoCriteria);

    int saveUser(UserVo user);

    List<UserVo> getUserListByMap(Map<String, Object> map);

    UserVo selectByPrimaryKey(String id);

    int updateByPrimaryKey(UserVo record);
}
