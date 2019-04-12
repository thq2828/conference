package com.haiqing.conference.controller;

import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.dto.ConferenceImpl;
import com.haiqing.conference.service.ConferenceService;
import com.haiqing.conference.util.EmptyUtility;
import com.haiqing.conference.util.PageUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 14:52
 * 会议管理接口类
 */
@Slf4j
@RestController
public class ConferenceController {
    /**
     * 最小的参会人数
     */
    public static final int MINIMUM_ATTENDANCE = 1;
    //默认分页的数据
    public static final int START_PAGE = 1;
    public static final int START_SIZE = 10;
    public static final int SORT_STARTTIME = 0;
    public static final int SORT_STARTTIME_DESC = 1;
    public static final int SORT_MEETTINGROOM = 2;
    public static final int SORT_PERDONNEL = 3;
    public static final int SORT_TITLE = 4;

    @Autowired
    private ConferenceService conferenceService;

    /**
     * 查询会议详情接口
     *
     * @param id
     * @return
     */
    @GetMapping("/conference/{id}")
    public ResultBean queryConference(@PathVariable(value = "id") Integer id) {
        log.info("- enter into method ConferenceController.queryConference,parameter id:{}", id);
        return conferenceService.getConference(id);
    }

    /**
     * 搜索栏接口
     *
     * @param meettingId
     * @param personnelId
     * @param title
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/conferences")
    public ResultBean queryConferences(@RequestParam(name = "meettingId", required = false) Integer meettingId,
                                       @RequestParam(name = "personnelId", required = false) Integer personnelId,
                                       @RequestParam(name = "title", required = false) String title,
                                       @RequestParam(name = "startTime", required = false) Long startTime,
                                       @RequestParam(name = "endTime", required = false) Long endTime,
                                       @RequestParam(name = "sortStartTime", required = false) Integer sortStartTime,
                                       @RequestParam(name = "sortMeettingId", required = false) Integer sortMeettingId,
                                       @RequestParam(name = "sortTitle", required = false) Integer sortTitle,
                                       @RequestParam(name = "sortPersonnelId", required = false) Integer sortPersonnelId,
                                       @RequestParam(name = "sortCreateAt", required = false) Integer sortCreateAt,
                                       @RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "size", required = false) Integer size, HttpServletResponse response) {
        log.info("- enter into method ConferenceController.queryConferences,parameter" +
                        " meettingId:{},personnelId:{},title:{},startTime:{}," +
                        "endTime:{},page:{},size:{},sortStartTime:{},sortMeettingId:{},sortTitle:{},sortPersonnelId:{}",
                meettingId, personnelId, title, startTime, endTime, page, size, sortStartTime, sortMeettingId, sortTitle, sortPersonnelId);
        //response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");

        //参数校验
        if (startTime != null && endTime != null) {
            if (startTime > endTime) {
                return new ResultBean(1025);
            }
        }
        if (page == null || page < 1) {
            page = START_PAGE;
        }
        if (size == null || size < 1) {
            size = START_SIZE;
        }
        Integer start = PageUtility.getStart(page, size);
        //把参数加入到map容器
        Map<String, Object> map = new HashMap<>();
        if (meettingId != null) {
            map.put("meettingId", meettingId);
        }
        if (personnelId != null) {
            map.put("personnelId", personnelId);
        }
        if (!EmptyUtility.strIsEmpty(title)) {
            map.put("title", title);
        }
        if (startTime != null) {
            map.put("startTime", startTime);
        }
        if (endTime != null) {
            map.put("endTime", endTime);
        }

        if (sortCreateAt==null && sortStartTime == null && sortMeettingId == null && sortPersonnelId == null && sortTitle == null) {
            map.put("sortCreateAt", 8);
        }
        if (sortCreateAt!=null){
            map.put("sortCreateAt", sortCreateAt);
        }
        if (sortStartTime != null) {
            map.put("sortStartTime", sortStartTime);
        }
        if (sortMeettingId != null) {
            map.put("sortMeettingId", sortMeettingId);
        }
        if (sortPersonnelId != null) {
            map.put("sortPersonnelId", sortPersonnelId);
        }
        if (sortTitle != null) {
            map.put("sortTitle", sortTitle);
        }
        ResultBean resultBean = conferenceService.getConferences(map, size, start);
        //当数据不为空时加入第几页数据
        if (!EmptyUtility.isNullOrEmpty(resultBean.getData())) {
            ((PageResultBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }


    /**
     * 新增参数判断
     */
    @PostMapping("/conference1")
    public ResultBean createConfenrece1(@RequestBody ConferenceImpl conference) {
        log.info("- enter into method ConferenceController.createConfenrece1,parameter" +
                " conference:{}", conference);

        //参数校验
        if (EmptyUtility.isNullOrEmpty(conference)) {
            return new ResultBean(1001);
        }
        if (conference.getMeettingId() == null) {
            return new ResultBean(1021);
        }
        if (conference.getPersonnelId() == null) {
            return new ResultBean(1022);
        }
        if (EmptyUtility.strIsEmpty(conference.getTitle())) {
            return new ResultBean(1023);
        }
        if (conference.getStartTime() == null || conference.getEndTime() == null) {
            return new ResultBean(1024);
        }

        if (conference.getEndTime() - conference.getStartTime() <= 0) {
            return new ResultBean(1025);
        }
        if (EmptyUtility.isNullOrEmpty(conference.getPersonnelIds()) || conference.getPersonnelIds().length < MINIMUM_ATTENDANCE) {
            return new ResultBean(1026);
        }
        //加入创建人和时间
        conference.setCreateAt(System.currentTimeMillis());
        conference.setCreateBy(1);
            return conferenceService.checkUser(conference);
    }



    /**
     * 新增会议接口
     *
     * @param conference 各参数不能为空，详情看具体实现
     * @return
     */

    @PostMapping("/conference")
    public ResultBean createConfenrece(@RequestBody ConferenceImpl conference) {
        log.info("- enter into method ConferenceController.createConfenrece,parameter" +
                " conference:{}", conference);

        //参数校验
        if (EmptyUtility.isNullOrEmpty(conference)) {
            return new ResultBean(1001);
        }
        if (conference.getMeettingId() == null) {
            return new ResultBean(1021);
        }
        if (conference.getPersonnelId() == null) {
            return new ResultBean(1022);
        }
        if (EmptyUtility.strIsEmpty(conference.getTitle())) {
            return new ResultBean(1023);
        }
        if (conference.getStartTime() == null || conference.getEndTime() == null) {
            return new ResultBean(1024);
        }

        if (conference.getEndTime() - conference.getStartTime() <= 0) {
            return new ResultBean(1025);
        }
        if (EmptyUtility.isNullOrEmpty(conference.getPersonnelIds()) || conference.getPersonnelIds().length < MINIMUM_ATTENDANCE) {
            return new ResultBean(1026);
        }
        //加入创建人和时间
        conference.setCreateAt(System.currentTimeMillis());
        conference.setCreateBy(1);
        return conferenceService.addConference(conference);
    }

    /**
     * 更新一条会议记录接口
     *
     * @param
     * @param conference
     * @return
     */
    @PutMapping("/conference")
    public ResultBean updateConference(@RequestBody ConferenceImpl conference) {
        log.info("- enter into method ConferenceController.updateConference,parameter" +
                ",conference:{}", conference);
        //参数校验
        if (EmptyUtility.isNullOrEmpty(conference)) {
            return new ResultBean(1001);
        }
        if (EmptyUtility.isNullOrEmpty(conference.getPersonnelIds()) || conference.getPersonnelIds().length < MINIMUM_ATTENDANCE) {
            return new ResultBean(1026);
        }
        if (conference.getStartTime() != null && conference.getEndTime() != null && conference.getEndTime() < conference.getStartTime()) {
            new ResultBean(1025);
        }
        //加入更新的数据
        conference.setUpdateAt(System.currentTimeMillis());
        conference.setUpdateBy(1);
        return conferenceService.putConference(conference);
    }

    /**
     * 会议管理删除接口
     *
     * @param id
     * @return
     */
    @DeleteMapping("conference/{id}")
    public ResultBean delConference(@PathVariable(value = "id") Integer id) {
        log.info("- enter into method ConferenceController.delConference,parameter" +
                " id:{}", id);

        return conferenceService.deleteConference(id);
    }

}
