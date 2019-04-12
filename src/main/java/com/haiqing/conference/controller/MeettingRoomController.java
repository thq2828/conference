package com.haiqing.conference.controller;

import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.pojo.MeettingRoom;
import com.haiqing.conference.service.MeettingRoomService;
import com.haiqing.conference.util.EmptyUtility;
import com.haiqing.conference.util.PageUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.haiqing.conference.controller.ConferenceController.START_PAGE;
import static com.haiqing.conference.controller.ConferenceController.START_SIZE;

/**
 * 会议室管理接口类
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 17:19
 * 会议室接口类
 */
@Slf4j
@RestController
public class MeettingRoomController {
    @Autowired
    private MeettingRoomService meettingRomService;

    /**
     * 根据id查询会议室详情
     * @param id
     * @return
     */
    @GetMapping("/MeettingRoom/{id}")
    public ResultBean queryMeettingRoon(@PathVariable(value = "id") Integer id) {
        log.info("- enter into method MeettingRoomController.queryMeettingRoon,parameter id:{}", id);
        return meettingRomService.getMeettingRoom(id);
    }

    /**
     * 会议室列表信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/MeettingRooms")
    public ResultBean queryMeettingRoons(@RequestParam(name = "page",required = false)Integer page,
                                         @RequestParam(name = "size",required = false)Integer size){
        log.info("- enter into method MeettingRoomController.queryMeettingRoons,parameter page:{},size:{}", page,size);
        System.out.println(Integer.MAX_VALUE);
        //参数校验
        if (page == null || page < 1) {
            page = START_PAGE;
        }
        if (size == null || size < 1) {
            size = START_SIZE;
        }
        Integer start = PageUtility.getStart(page, size);
        ResultBean resultBean =meettingRomService.getMeettingRooms(start,size);
        //加入分页的数据
        if (!EmptyUtility.isNullOrEmpty(resultBean.getData())){
            ((PageResultBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }

    /**
     * 根据姓名查询会议室详情
     * @param name
     * @return
     */
    @GetMapping("/MeettingRoomName")
    public ResultBean queryMeettingRoonsByName(@RequestParam(name = "name")String name){
        log.info("- enter into method MeettingRoomController.queryMeettingRoonsByName,parameter name:{}", name);
        //参数校验
        if (EmptyUtility.strIsEmpty(name)){
            return new ResultBean(1043);
        }
        return meettingRomService.getMeettingRoomByName(name);

    }
    /**
     * 增加一个会议室
     *
     * @param meettingRoom
     * @return
     */
    @PostMapping("/MeettingRoom")
    public ResultBean createMeettingRoom(@RequestBody MeettingRoom meettingRoom) {
        log.info("- enter into method MeettingRoomController.createMeettingRoom,parameter meettingRoom:{}", meettingRoom);
        //参数校验
        if (EmptyUtility.isNullOrEmpty(meettingRoom)) {
            return new ResultBean(1001);
        }
        if (EmptyUtility.strIsEmpty(meettingRoom.getAddress())) {
            return new ResultBean(1037);
        }
        if (EmptyUtility.strIsEmpty(meettingRoom.getName())) {
            return new ResultBean(1036);
        }
        //加入其余数据
        meettingRoom.setCreateAt(System.currentTimeMillis());
        meettingRoom.setCreateBy(1);

        return meettingRomService.addMeettingRoom(meettingRoom);

    }

    /**
     * 更新一个会议室
     *
     * @param
     * @param meettingRoom
     * @return
     */
    @PutMapping("/MeettingRoom")
    public ResultBean putMeettingRoom(
            @RequestBody MeettingRoom meettingRoom) {
        log.info("- enter into method MeettingRoomController.putMeettingRoom,parameter ,meettingRoom:{}", meettingRoom);
        //加入更新时间等数据

        meettingRoom.setUpdateAt(System.currentTimeMillis());
        meettingRoom.setUpdateBy(1);
        return meettingRomService.putMeettingRoom(meettingRoom);

    }

    /**
     * 删除一个会议室
     *
     * @param id
     * @return
     */
        @DeleteMapping("/MeettingRoom/{id}")
    public ResultBean delMeettingRoom(@PathVariable(value = "id") Integer id) {
        log.info("- enter into method MeettingRoomController.putMeettingRoom,parameter id : {}", id);
        return meettingRomService.delMeettingRoom(id);
    }


}
