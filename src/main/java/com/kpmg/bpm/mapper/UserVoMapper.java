package com.kpmg.bpm.mapper;

import com.kpmg.bpm.vo.UserVo;
import com.kpmg.bpm.vo.UserVoCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserVoMapper {
    int countByExample(UserVoCriteria example);

    int deleteByExample(UserVoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UserVo record);

    int insertSelective(UserVo record);

    List<UserVo> selectByExample(UserVoCriteria example);

    UserVo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserVo record, @Param("example") UserVoCriteria example);

    int updateByExample(@Param("record") UserVo record, @Param("example") UserVoCriteria example);

    int updateByPrimaryKeySelective(UserVo record);

    int updateByPrimaryKey(UserVo record);
}