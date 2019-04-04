package com.haiqing.conference.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created By haiqing
 * Date: 2019/3/28
 * Time: 15:13
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConferenceAndPersonnelMapperImplTest {
    @Autowired
    ConferenceAndPersonnelMapper conferenceAndPersonnelMapper;
    @Test
    public void insert() {
        System.out.println(conferenceAndPersonnelMapper.insert(1,1));




    }

    @Test
    public void selectBySeletive() {
        Map<String,Object> map =new HashMap<>();
        map.put("conferenceId",1);
        //map.put("personnelId",2);
        //Integer[] map1=conferenceAndPersonnelMapper.selectBySeletive(map);
        //System.out.println(Arrays.toString(map1));

    }

    @Test
    public void deleteByconferenceId() {
        System.out.println(conferenceAndPersonnelMapper.deleteByconferenceId(2));
    }
}