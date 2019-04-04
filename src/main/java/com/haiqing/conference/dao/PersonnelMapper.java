package com.haiqing.conference.dao;

import com.haiqing.conference.pojo.Personnel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonnelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Personnel record);

    Personnel selectByPrimaryKey(Integer id);

    List<Personnel> selectAll(Map<String,Object> map);

    int updateByPrimaryKey(Personnel record);

    /**
     * 模糊根据姓名查询
     * @param name
     * @return
     */
    List<Personnel> selectByName(@Param("name") String name);

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    Personnel selectByNameEqual(@Param("name")String name);

    /**
     * 动态的更新人员信息
     * @param personnel
     * @return
     */
    int updateBySelective(Personnel personnel);

    List<Personnel> selectByIds(Map<String,Object> map);
}