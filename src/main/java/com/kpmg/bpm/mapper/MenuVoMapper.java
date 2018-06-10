package com.kpmg.bpm.mapper;

import com.kpmg.bpm.vo.MenuVo;
import com.kpmg.bpm.vo.MenuVoCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MenuVoMapper {
    int countByExample(MenuVoCriteria example);

    int deleteByExample(MenuVoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(MenuVo record);

    int insertSelective(MenuVo record);

    List<MenuVo> selectByExample(MenuVoCriteria example);

    MenuVo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MenuVo record, @Param("example") MenuVoCriteria example);

    int updateByExample(@Param("record") MenuVo record, @Param("example") MenuVoCriteria example);

    int updateByPrimaryKeySelective(MenuVo record);

    int updateByPrimaryKey(MenuVo record);
}