package com.haiqing.conference.dao;

import com.haiqing.conference.dto.ConferenceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 10:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConferenceMapperTest {
    @Autowired
    private  ConferenceMapper conferenceMapper;
    private static ConferenceImpl conference;
    static {
        conference = new ConferenceImpl();
        conference.setMeettingId(2);
        conference.setPersonnelId(2);
        conference.setTitle("前端评审");
        conference.setStartTime(System.currentTimeMillis());
        conference.setEndTime(System.currentTimeMillis()+1000*60*60*2);
        conference.setPresonnels(new Integer[]{1,2});
        conference.setCreateAt(System.currentTimeMillis());
        conference.setCreateBy(1);
        conference.setUpdateAt(System.currentTimeMillis());
        conference.setUpdateBy(1);

    }



    @Test
    public void deleteByPrimaryKey() {
        System.out.println(conferenceMapper.deleteByPrimaryKey(3)+"///////////////////////////////");
    }

    @Test
    public void insert() {

        System.out.println(conference.getId());
        System.out.println(conferenceMapper.insert(conference)+"/////////////////////////////////////////");
        System.out.println(conference.getId());
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(conferenceMapper.selectByPrimaryKey(3));
    }

    @Test
    public void selectAll() {
        System.out.println(conferenceMapper.selectAll());
    }

    @Test
    public void updateByPrimaryKey() {
        conference.setId(3);
        conference.setTitle("后端评审");
        System.out.println(conferenceMapper.updateByPrimaryKey(conference));
    }

    @Test
    public void selectByPrimaryKeySelective() {
        Map<String,Object> map =new HashMap<>();
        //map.put("meettingId",2);
        //map.put("personnelId",1);
        map.put("title","会议");
        map.put("startTime",1005);
        map.put("endTime",9998);
        //map.put("sortStartTime",1);
        System.out.println(conferenceMapper.selectByPrimaryKeySelective(map));




    }

    @Test
    public void selectByCount() {
        System.out.println(conferenceMapper.selectByCount());
    }
}