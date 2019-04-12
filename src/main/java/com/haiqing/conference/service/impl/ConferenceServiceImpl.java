package com.haiqing.conference.service.impl;

import com.haiqing.conference.dao.ConferenceAndPersonnelMapper;
import com.haiqing.conference.dao.ConferenceMapper;
import com.haiqing.conference.dao.PersonnelMapper;
import com.haiqing.conference.dto.ConferenceImplVo;
import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.exception.IsConferenceException;
import com.haiqing.conference.pojo.Conference;
import com.haiqing.conference.dto.ConferenceImpl;
import com.haiqing.conference.pojo.Personnel;
import com.haiqing.conference.service.ConferenceService;
import com.haiqing.conference.util.EmptyUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 会议管理业务实现类
 * Created By haiqing
 * Date: 2019/3/28
 * Time: 9:06
 */
@Slf4j
@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceMapper conferenceMapper;
    @Autowired
    private ConferenceAndPersonnelMapper conferenceAndPersonnelMapper;
    @Autowired
    private PersonnelMapper personnelMapper;

    /**
     * 获取会议详情
     * F
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean getConference(Integer id) {
        log.info("- enter into method ConferenceServiceImpl.getConference,parameter" +
                " id:{}", id);
        Conference conference = conferenceMapper.selectByPrimaryKey(id);
        if (EmptyUtility.isNullOrEmpty(conference)) {
            return new ResultBean(1027);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("conferenceId", id);
        List<Map<String, Object>> maps = conferenceAndPersonnelMapper.selectBySeletive(map);
        List<Integer> personnelIds = new ArrayList<>();
        for (Map<String, Object> map1 : maps) {
            personnelIds.add((Integer) map1.get("per_id"));
        }
        ConferenceImpl conference1 = new ConferenceImpl();
        conference1.setId(conference.getId());
        conference1.setMeettingId(conference.getMeettingId());
        conference1.setPersonnelId(conference.getPersonnelId());
        conference1.setTitle(conference.getTitle());
        conference1.setStartTime(conference.getStartTime());
        conference1.setEndTime(conference.getEndTime());
        conference1.setPersonnelIds(personnelIds.toArray(new Integer[0]));
        return new ResultBean<Conference>(1, conference1);
    }

    /**
     * 搜索栏对应的业务方法
     *
     * @param start
     * @param map
     * @param size
     * @return
     */
    @Override
    public PageResultBean getConferences(Map<String, Object> map, Integer size, Integer start) {
        log.info("- enter into method ConferenceServiceImpl.getConferences,parameter" +
                " map:{}，size:{}，start:{}", map.toString(), size, start);
        //查询出数据的总数
        List<ConferenceImplVo> conferences = conferenceMapper.selectByPrimaryKeySelective(map);
        if (EmptyUtility.isNullOrEmpty(conferences)) {
            return new PageResultBean(1028);
        }
        //判断数据总数与size比较
        int totalRecord = conferences.size();
        if (totalRecord < size) {
            log.info("总数小于size直接返回数据");
            return new PageResultBean<List<ConferenceImplVo>>(1, size, totalRecord, conferences);
        }
        //totalRecord>size进行分页查询
        map.put("size", size);
        map.put("start", start);
        conferences = conferenceMapper.selectByPrimaryKeySelective(map);
        return new PageResultBean<List<ConferenceImplVo>>(1, size, totalRecord, conferences);
    }

    /**
     * 新增一条会议业务接口
     *
     * @param conference
     * @return
     */
    @Override
    public ResultBean addConference(ConferenceImpl conference) {
        log.info("- enter into method ConferenceServiceImpl.addConference,parameter" +
                " conference:{}", conference);
        //判断会议室是否被占用
        if (isConferenceByMeettingRoom(conference)) {
            return new ResultBean(1030);
        }
        //判读组织人员是否在这段时间有会议
        if (isConferenceByOrganizer(conference)) {
            return new ResultBean(1029);
        }


        if (conference.getId() == null) {
            //增加会议管理表中的记录
            int i = conferenceMapper.insert(conference);
            if (i < 1) {
                return new ResultBean(0);
            }
        } else {
            int i = conferenceMapper.updateByPrimaryKey(conference);
            if (i < 1) {
                return new ResultBean(0);
            }
            conferenceAndPersonnelMapper.deleteByconferenceId(conference.getId());
        }
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(conference.getPersonnelIds()));
        conferenceAndPersonnelMapper.insertByMultiterm(conference.getId(), list);
        return new ResultBean<Map>(1);
    }

    /**
     * 增加参数判断
     *
     * @param conference
     * @return
     */
    @Override
    public ResultBean checkUser(ConferenceImpl conference) {
        log.info("- enter into method ConferenceServiceImpl.addConference,parameter" +
                " conference:{}", conference);

        if (conference.getPersonnelId() != null) {
            if (isConferenceByOrganizer(conference)) {
                return new ResultBean(1029);
            }
        }
        if (conference.getMeettingId() != null) {
            if (isConferenceByMeettingRoom(conference)) {
                return new ResultBean(1030);
            }
        }
        //判断参会人员是否参加多个会议
        List<Integer> isPersonnel = isConference(conference);
        Map<String, Object> map = new HashMap<>();
        List<Personnel> conferPer = null;
        if (!EmptyUtility.isNullOrEmpty(isPersonnel)) {
            map.put("ids", isPersonnel);
            conferPer = personnelMapper.selectByIds(map);
            map.clear();
            map.put("conferPer", conferPer);
            return new ResultBean<Map>(300, "已经在该时间段参加其他会议，是否确认提交本次会议", map);
        }
        return new ResultBean(1);
    }

    /**
     * 更新一条会议接口
     *
     * @param conference
     * @return
     */
    @Override
    public ResultBean putConference(ConferenceImpl conference) {
        if (conference.getPersonnelId() != null) {
            if (isConferenceByOrganizer(conference)) {
                return new ResultBean(1029);
            }
        }
        if (conference.getMeettingId() != null) {
            if (isConferenceByMeettingRoom(conference)) {
                return new ResultBean(1030);
            }
        }
        //更行关系表的数据
        if (!EmptyUtility.isNullOrEmpty(conference.getPersonnelIds())) {
            //先删除会议室的所有参会人员
            int i1 = conferenceAndPersonnelMapper.deleteByconferenceId(conference.getId());
            //插入更新的参会人员
            //判断参会人员是否参加多个会议
            List<Integer> isPersonnel = isConference(conference);
            List<Integer> list = new ArrayList<Integer>(Arrays.asList(conference.getPersonnelIds()));
            //批量插入数据
            int i2 = conferenceAndPersonnelMapper.insertByMultiterm(conference.getId(), list);

            Map<String, Object> map = new HashMap<>();
            List<Personnel> conferPer = null;
            if (!EmptyUtility.isNullOrEmpty(isPersonnel)) {
                map.put("ids", isPersonnel);
                conferPer = personnelMapper.selectByIds(map);
            }
            map.clear();
            map.put("conferPer", conferPer);

            return new ResultBean<Map>(1, map);
        }
        int i = conferenceMapper.updateByPrimaryKeySelective(conference);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }


    /**
     * 删除一条会议接口
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean deleteConference(Integer id) {
        log.info("- enter into method ConferenceServiceImpl.deleteConference,parameter" +
                " id:{}", id);
        int i = conferenceMapper.deleteByPrimaryKey(id);
        int i1 = conferenceAndPersonnelMapper.deleteByconferenceId(id);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }

    /**
     * 判断会议室是否在这段时间被占用,如果有返回true
     *
     * @param conference
     * @return
     */
    private boolean isConferenceByMeettingRoom(Conference conference) {
        log.info("- enter into method ConferenceServiceImpl.isConferenceByMeettingRoom,parameter" +
                " conference:{}", conference);
        //判断会议室是否在这个时间段有会议
        Map<String, Object> map = new HashMap();
        map.put("id", conference.getId());
        map.put("meettingId", conference.getMeettingId());
        map.put("startTime", conference.getStartTime());
        map.put("endTime", conference.getEndTime());
        List<ConferenceImplVo> conferences = conferenceMapper.selectByPrimaryKeySelective(map);
        System.out.println(conferences);
        if (EmptyUtility.isNullOrEmpty(conferences)) {
            return false;
        }
        return true;
    }

    /**
     * 该方法主要判断该时间段是否有又该组织人员的会议，如果有返回true
     *
     * @param conference
     * @return
     */
    private boolean isConferenceByOrganizer(Conference conference) {

        //判断组织人员是否在这个时间段有会议
        Map<String, Object> map = new HashMap();
        map.put("id", conference.getId());
        map.put("personnelId", conference.getPersonnelId());
        map.put("startTime", conference.getStartTime());
        map.put("endTime", conference.getEndTime());
        List<ConferenceImplVo> conferences = conferenceMapper.selectByPrimaryKeySelective(map);
        if (EmptyUtility.isNullOrEmpty(conferences)) {
            return false;
        }
        return true;
    }


    /**
     * 该方法主要判断所选的参会人员在这段时间是否有会议参加，如果有抛出运行时异常，在全局异常进行捕获用于提示用户
     *
     * @param conference
     */
    private List<Integer> isConference(ConferenceImpl conference) {
        //把这个时间的信息装入Map容器中
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", conference.getStartTime());
        map.put("endTime", conference.getEndTime());
        //查询出这个时间段的全部会议记录
        List<ConferenceImplVo> conferences = conferenceMapper.selectByPrimaryKeySelective(map);
        //删除刚插入或者更新的那个元素
        conferences.removeIf(conferenceIterator -> conference.getId() == conferenceIterator.getId());
        if (EmptyUtility.isNullOrEmpty(conferences)) {
            return null;
        }
        List<Integer> personnels = new ArrayList<>();
        //遍历这个时间的所有会议记录
        for (ConferenceImplVo conference1 : conferences) {
            //查询出每条会议的参会人员
            map.clear();
            map.put("conferenceId", conference1.getId());
            List<Map<String, Object>> maps = conferenceAndPersonnelMapper.selectBySeletive(map);
            //遍历出参会人员
            for (Map<String, Object> map1 : maps) {
                //判断这个参会人员是否在这段时间有会议
                if (Arrays.asList(conference.getPersonnelIds()).contains((Integer) map1.get("per_id"))) {
                    personnels.add((Integer) map1.get("per_id"));
                }
            }
        }
        return personnels;
    }

}
