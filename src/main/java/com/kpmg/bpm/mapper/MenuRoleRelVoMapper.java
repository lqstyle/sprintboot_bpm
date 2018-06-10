package com.kpmg.bpm.mapper;

import com.kpmg.bpm.vo.MenuRoleRelVoCriteria;
import com.kpmg.bpm.vo.MenuRoleRelVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface MenuRoleRelVoMapper {
    int countByExample(MenuRoleRelVoCriteria example);

    int deleteByExample(MenuRoleRelVoCriteria example);

    int deleteByPrimaryKey(MenuRoleRelVo key);

    int insert(MenuRoleRelVo record);

    int insertSelective(MenuRoleRelVo record);

    List<MenuRoleRelVo> selectByExample(MenuRoleRelVoCriteria example);

    int updateByExampleSelective(@Param("record") MenuRoleRelVo record, @Param("example") MenuRoleRelVoCriteria example);

    int updateByExample(@Param("record") MenuRoleRelVo record, @Param("example") MenuRoleRelVoCriteria example);

    List<Map<String, Object>> getMenuList(Map<String, Object> map);
}