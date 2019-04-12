package com.haiqing.conference.dto;

import com.haiqing.conference.pojo.Conference;
import com.haiqing.conference.pojo.MeettingRoom;
import com.haiqing.conference.pojo.Personnel;

/**
 * Created By haiqing
 * Date: 2019/4/11
 * Time: 10:02
 */
public class ConferenceImplVo extends Conference {

    private Personnel personnel;
    private MeettingRoom meettingRoom;

    public ConferenceImplVo() {
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public MeettingRoom getMeettingRoom() {
        return meettingRoom;
    }

    public void setMeettingRoom(MeettingRoom meettingRoom) {
        this.meettingRoom = meettingRoom;
    }

    @Override
    public String toString() {
        return "ConferenceImplVo{" +
                "personnel=" + personnel +
                ", meettingRoom=" + meettingRoom +
                "} " + super.toString();
    }
}
