package com.haiqing.conference.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created By haiqing
 * Date: 2019/3/28
 * Time: 15:02
 * 人员与会议关系表的操作
 */
@Mapper
public interface ConferenceAndPersonnelMapper {
    /**
     * 关系表中插入一条数据
     * @param conferenceId
     * @param personnelId
     * @return
     */
    int insert(@Param("conferenceId")Integer conferenceId,@Param("personnelId")Integer personnelId);

    /**
     * 动态查询关系表中的数据
     * @param map
     * @return
     */
    List<Map<String,Object>> selectBySeletive(Map<String,Object> map);

    int deleteByconferenceId(Integer conferenceId);

    int insertByMultiterm(@Param("conferenceId")Integer conferenceId,@Param("personnerlIds") List<Integer> personnerlIds);

}
