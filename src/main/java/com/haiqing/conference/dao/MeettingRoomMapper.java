package com.haiqing.conference.dao;

import com.haiqing.conference.pojo.MeettingRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
public interface MeettingRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeettingRoom record);

    MeettingRoom selectByPrimaryKey(Integer id);

    List<MeettingRoom> selectAll(Map<String,Object> map);

    int updateByPrimaryKey(MeettingRoom record);

    List<MeettingRoom> selectByName(String name);

    int updateBySelective(MeettingRoom meettingRoom);
}