package com.haiqing.conference.service.impl;

import com.haiqing.conference.dao.MeettingRoomMapper;
import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.pojo.Conference;
import com.haiqing.conference.pojo.MeettingRoom;
import com.haiqing.conference.service.MeettingRoomService;
import com.haiqing.conference.util.EmptyUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By haiqing
 * Date: 2019/3/29
 * Time: 14:12
 */
@Slf4j
@Service
public class MeettingRoomServiceImpl implements MeettingRoomService {

    @Autowired
    private MeettingRoomMapper meettingRoomMapper;

    /**
     * 根据id查询会议室详情
     *
     * @param id
     * @return
     */
    public ResultBean getMeettingRoom(Integer id) {
        log.info("- enter into method MeettingRomServiceImpl.getMeettingRoom,parameter" +
                " id:{}", id);
        MeettingRoom meettingRoom =meettingRoomMapper.selectByPrimaryKey(id);
        if (EmptyUtility.isNullOrEmpty(meettingRoom)){
            return new ResultBean(1039);
        }
        return new ResultBean<MeettingRoom>(1,meettingRoom);
    }

    /**
     * 根据会议室名字查询会议室详情
     * @param name
     * @return
     */
    public ResultBean getMeettingRoomByName(String name) {
        log.info("- enter into method MeettingRomServiceImpl.getMeettingRoomByName,parameter" +
                " name:{}", name);
        List<MeettingRoom> meettingRoom =meettingRoomMapper.selectByName(name);
        if (EmptyUtility.isNullOrEmpty(meettingRoom)){
            return new ResultBean<List<MeettingRoom>>(1039,meettingRoom);
        }
        return new ResultBean<List<MeettingRoom>>(1,meettingRoom);
    }

    /**
     * 查询全部数据并进行分页
     * @param start
     * @param size
     * @return
     */
    public PageResultBean getMeettingRooms(Integer start,Integer size){
        log.info("- enter into method MeettingRomServiceImpl.getMeettingRoomByName,parameter" +
                " start:{},size:{}", start,size);
        Map<String,Object> map=new HashMap();
        List<MeettingRoom> meettingRooms =meettingRoomMapper.selectAll(map);
        if (EmptyUtility.isNullOrEmpty(meettingRooms)){
            return new PageResultBean(1040);
        }

        //判断数据总数与size比较
        int totalRecord = meettingRooms.size();
        if (totalRecord < size&&start==0) {
            log.info("总数小于size直接返回数据");
            return new PageResultBean<List<MeettingRoom>>(1, size, totalRecord, meettingRooms);
        }
        //totalRecord>size进行分页查询
        map.put("size", size);
        map.put("start", start);
        meettingRooms =meettingRoomMapper.selectAll(map);
        return new PageResultBean<List<MeettingRoom>>(1,size,totalRecord,meettingRooms);
    }
    /**
     * 新增会议室
     *
     * @param meettingRoom
     * @return
     */
    @Override
    public ResultBean addMeettingRoom(MeettingRoom meettingRoom) {
        log.info("- enter into method MeettingRomServiceImpl.addMeettingRoom,parameter" +
                " meettingRoom:{}", meettingRoom);
        //判重
        List<MeettingRoom> meettingRooms = meettingRoomMapper.selectByName(meettingRoom.getName());

        if (!EmptyUtility.isNullOrEmpty(meettingRooms)) {
            return new ResultBean(1038);
        }
        int i = meettingRoomMapper.insert(meettingRoom);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }

    /**
     * 更新一个会议室记录
     *
     * @param meettingRoom
     * @return
     */
    @Override
    public ResultBean putMeettingRoom(MeettingRoom meettingRoom) {
        log.info("- enter into method MeettingRomServiceImpl.putMeettingRoom,parameter" +
                " meettingRoom:{}", meettingRoom);
        if (!EmptyUtility.strIsEmpty(meettingRoom.getName())) {
            //判重
            List<MeettingRoom> meettingRooms = meettingRoomMapper.selectByName(meettingRoom.getName());
            if (!EmptyUtility.isNullOrEmpty(meettingRooms)) {
                return new ResultBean(1038);
            }
        }
        int i = meettingRoomMapper.updateBySelective(meettingRoom);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }

    /**
     * 删除一个会议室记录
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean delMeettingRoom(Integer id) {
        log.info("- enter into method MeettingRomServiceImpl.delMeettingRoom,parameter" +
                " id:{}", id);
        int i = meettingRoomMapper.deleteByPrimaryKey(id);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }
}
