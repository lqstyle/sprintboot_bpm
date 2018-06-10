package com.kpmg.bpm.service;

import com.kpmg.bpm.vo.RoleVo;
import com.kpmg.bpm.vo.RoleVoCriteria;

import java.util.List;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: ${todo}
 * @date 09/06/2018 10:42 上午
 */
public interface RoleService {

    List<RoleVo> getRoleList(RoleVoCriteria example);
}
