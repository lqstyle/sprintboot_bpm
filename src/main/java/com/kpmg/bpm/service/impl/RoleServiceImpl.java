package com.kpmg.bpm.service.impl;

import com.kpmg.bpm.mapper.RoleVoMapper;
import com.kpmg.bpm.service.RoleService;
import com.kpmg.bpm.vo.RoleVo;
import com.kpmg.bpm.vo.RoleVoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: ${todo}
 * @date 09/06/2018 10:42 上午
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleVoMapper roleVoMapper;

    @Override
    public List<RoleVo> getRoleList(RoleVoCriteria example) {
        return roleVoMapper.selectByExample(example);
    }
}
