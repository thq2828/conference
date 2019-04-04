package com.haiqing.conference.dao;

import com.haiqing.conference.pojo.MeettingRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 10:21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeettingRoomMapperTest {
    @Autowired
    private MeettingRoomMapper meettingRoomMapper;
    private static MeettingRoom meettingRoom;

    static {
        meettingRoom = new MeettingRoom();
        meettingRoom.setName("会议室1");
        meettingRoom.setAddress("康拓普公司二楼211");
        meettingRoom.setCreateAt(System.currentTimeMillis());
        meettingRoom.setCreateBy(1);
        meettingRoom.setUpdateAt(System.currentTimeMillis());
        meettingRoom.setUpdateBy(1);
    }


    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        System.out.println(meettingRoomMapper.insert(meettingRoom)+"//////////////////////////////");
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(meettingRoomMapper.selectByPrimaryKey(1));

    }

    //@Test
    //public void selectAll() {
    //    System.out.println(meettingRoomMapper.selectAll());
    //}

    @Test
    public void updateByPrimaryKey() {
    }
}