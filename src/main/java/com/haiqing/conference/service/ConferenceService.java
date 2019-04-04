package com.haiqing.conference.service;

import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.dto.ConferenceImpl;
import com.haiqing.conference.exception.IsConferenceException;

import java.util.Map;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 15:15
 * 会议管理业务接口
 */
public interface ConferenceService {
    /**
     * 获取会议详情
     * @param id
     * @return
     */
     ResultBean getConference(Integer id);

    /**
     * 动态查询会议记录
     * @param map
     * @return
     */
     PageResultBean getConferences(Map<String,Object> map,Integer size,Integer start);

    /**
     * 增加一调会议
     * @param conference
     * @return
     */
     ResultBean addConference(ConferenceImpl conference) throws IsConferenceException;

    /**
     * 更新一条会议记录
     * @param conference
     * @return
     */
     ResultBean putConference(ConferenceImpl conference) throws IsConferenceException;

    /**
     * 删除一条会议记录
     * @param id
     * @return
     */
     ResultBean deleteConference(Integer id);
}
