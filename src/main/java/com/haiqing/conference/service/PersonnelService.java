package com.haiqing.conference.service;

import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.pojo.Personnel;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 17:23
 * 人员业务接口
 */
public interface PersonnelService {

    /**
     * 根据id获取用户详情
     * @param id
     * @return
     */
    ResultBean getPersonner(Integer id);

    /**
     * 根据姓名获取用户详情
     * @param name
     * @return
     */
    ResultBean getPersonnerByName(String name);

    /**
     * 获取人员列表进行分页
     * @param start
     * @param size
     * @return
     */
    PageResultBean getPersonners(Integer start,Integer size);

    /**
     * 根据数组内人员的信息
     * @param ids
     * @return
     */
    ResultBean getPersonnersByIds(Integer... ids);


    /**
     * 新增一个人员
     * @param personnel
     * @return
     */
    ResultBean addPersonnel(Personnel personnel);

    /**
     * 更新一个人员信息
     * @param personnel
     * @return
     */
    ResultBean putPersonnel(Personnel personnel);

    /**
     * 删除一个人员
     * @param id
     * @return
     */
    ResultBean delPersonnel(Integer id);

}
