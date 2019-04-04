package com.haiqing.conference.service;

import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.pojo.MeettingRoom;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 17:23
 * 会议室业务接口
 */
public interface MeettingRoomService {
    /**
     * 根据id查询会议室详情接口
     * @param id
     * @return
     */
     ResultBean getMeettingRoom(Integer id);

    /**
     * 根据姓名查询会议室详情接口
     * @param name
     * @return
     */
     ResultBean getMeettingRoomByName(String name);

    /**
     * 查询全部会议室并进行分页
     * @param start
     * @param size
     * @return
     */
     PageResultBean getMeettingRooms(Integer start, Integer size);
    /**
     * 新增会议室
     * @param meettingRoom
     * @return
     */
    ResultBean addMeettingRoom(MeettingRoom meettingRoom);

    /**
     * 更新会议室
     * @param meettingRoom
     * @return
     */
    ResultBean putMeettingRoom(MeettingRoom meettingRoom);

    /**
     * 删除会议室
     * @param id
     * @return
     */
    ResultBean delMeettingRoom(Integer id);
}
