package com.kpmg.bpm.mapper;

import com.kpmg.bpm.vo.RoleVo;
import com.kpmg.bpm.vo.RoleVoCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleVoMapper {
    int countByExample(RoleVoCriteria example);

    int deleteByExample(RoleVoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(RoleVo record);

    int insertSelective(RoleVo record);

    List<RoleVo> selectByExample(RoleVoCriteria example);

    RoleVo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleVo record, @Param("example") RoleVoCriteria example);

    int updateByExample(@Param("record") RoleVo record, @Param("example") RoleVoCriteria example);

    int updateByPrimaryKeySelective(RoleVo record);

    int updateByPrimaryKey(RoleVo record);
}