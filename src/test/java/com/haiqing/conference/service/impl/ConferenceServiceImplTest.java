package com.haiqing.conference.service.impl;

import com.haiqing.conference.service.ConferenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created By haiqing
 * Date: 2019/3/29
 * Time: 11:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConferenceServiceImplTest {
    @Autowired
    private ConferenceService conferenceService;

    @Test
    public void getConference() {
    }

    @Test
    public void getConferences() {
        Map<String,Object> map=new HashMap();
        map.put("title","会议");
        map.put("sortStartTime",1);
        System.out.println(conferenceService.getConferences(map,10,0));



    }

    @Test
    public void addConference() {
    }

    @Test
    public void putConference() {
    }

    @Test
    public void deleteConference() {
    }
}