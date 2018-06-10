package com.kpmg.bpm.mapper;

import com.kpmg.bpm.vo.UserRoleRelVOCriteria;
import com.kpmg.bpm.vo.UserRoleRelVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRoleRelVOMapper {
    int countByExample(UserRoleRelVOCriteria example);

    int deleteByExample(UserRoleRelVOCriteria example);

    int deleteByPrimaryKey(UserRoleRelVO key);

    int insert(UserRoleRelVO record);

    int insertSelective(UserRoleRelVO record);

    List<UserRoleRelVO> selectByExample(UserRoleRelVOCriteria example);

    int updateByExampleSelective(@Param("record") UserRoleRelVO record, @Param("example") UserRoleRelVOCriteria example);

    int updateByExample(@Param("record") UserRoleRelVO record, @Param("example") UserRoleRelVOCriteria example);
}