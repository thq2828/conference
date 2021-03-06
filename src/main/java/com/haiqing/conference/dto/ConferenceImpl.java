package com.haiqing.conference.dto;

import com.haiqing.conference.pojo.Conference;

import java.util.Arrays;

/**
 * 该类继承实体类Conference,用于前端传送数据，里面添加了一个字段personnels 类型：Integer[] 主要是参会人员的id的集合
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 15:50
 */
public class ConferenceImpl extends Conference {
    private Integer[] personnelIds;

    public ConferenceImpl() {
    }

    public ConferenceImpl(Integer[] personnelIds) {
        this.personnelIds = personnelIds;
    }

    public Integer[] getPersonnelIds() {
        return personnelIds;
    }

    public void setPersonnelIds(Integer[] personnelIds) {
        this.personnelIds = personnelIds;
    }

    @Override
    public String toString() {
        return "ConferenceImpl{" +
                "personnelIds=" + Arrays.toString(personnelIds) +
                "} " + super.toString();
    }
}
