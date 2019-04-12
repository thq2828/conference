package com.haiqing.conference.dao;

import com.haiqing.conference.dto.ConferenceImplVo;
import com.haiqing.conference.pojo.Conference;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConferenceMapper {

     int deleteByPrimaryKey(Integer id);

     int insert(Conference record);

     Conference selectByPrimaryKey(Integer id);

     List<ConferenceImplVo> selectAll();

     int updateByPrimaryKey(Conference record);

     /**
      * @description 该方法对应的动态查询会议管理方法
      * @param map 动态的条件
      * @return 查询出来的数据（多条）
      */
     List<ConferenceImplVo> selectByPrimaryKeySelective(Map<String,Object> map);

     /**
      * @description 查询出会议的总数
      * @return 总数
      */
     int selectByCount();

     /**
      * @description 动态的更新一条会议管理
      * @param conference
      * @return
      */
     int updateByPrimaryKeySelective(Conference conference);
}