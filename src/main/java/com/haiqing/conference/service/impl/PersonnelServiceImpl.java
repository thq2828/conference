package com.haiqing.conference.service.impl;

import com.haiqing.conference.dao.PersonnelMapper;
import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.pojo.MeettingRoom;
import com.haiqing.conference.pojo.Personnel;
import com.haiqing.conference.service.PersonnelService;
import com.haiqing.conference.util.EmptyUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员业务实现接口
 * Created By haiqing
 * Date: 2019/3/29
 * Time: 14:13
 */
@Slf4j
@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired
    private PersonnelMapper personnelMapper;

    /**
     * 根据id查询人员信息
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean getPersonner(Integer id) {
        log.info("- enter into method PersonnelServiceImpl.getPersonner,parameter id:{}", id);
        Personnel personnel = personnelMapper.selectByPrimaryKey(id);
        if (EmptyUtility.isNullOrEmpty(personnel)) {
            return new ResultBean(1046);
        }
        return new ResultBean<Personnel>(1, personnel);
    }

    /**
     * 根据姓名查询人员信息
     *
     * @param name
     * @return
     */
    @Override
    public ResultBean getPersonnerByName(String name) {
        log.info("- enter into method PersonnelServiceImpl.getPersonnerByName,parameter name:{}", name);
        List<Personnel> personnels = personnelMapper.selectByName(name);
        if (EmptyUtility.isNullOrEmpty(personnels)) {
            return new ResultBean<List<Personnel>>(1046,personnels);
        }
        return new ResultBean<List<Personnel>>(1, personnels);
    }

    /**
     * 人员列表查询
     *
     * @param start
     * @param size
     * @return
     */
    @Override
    public PageResultBean getPersonners(Integer start, Integer size) {
        log.info("- enter into method PersonnelServiceImpl.getPersonners,parameter start:{},size:{}", start, size);
        Map<String, Object> map = new HashMap<>();
        List<Personnel> personnels = personnelMapper.selectAll(map);
        if (EmptyUtility.isNullOrEmpty(personnels)) {
            return new PageResultBean(1047);
        }
        //判断数据总数与size比较
        int totalRecord = personnels.size();
        if (totalRecord < size && start == 0) {
            log.info("总数小于size直接返回数据");
            return new PageResultBean<List<Personnel>>(1, size, totalRecord, personnels);
        }
        //totalRecord>size进行分页查询
        map.put("size", size);
        map.put("start", start);
        personnels = personnelMapper.selectAll(map);
        return new PageResultBean<List<Personnel>>(1, size, totalRecord, personnels);

    }

    /**
     * 根据ids查询人员列表
     *
     * @param ids
     * @return
     */
    @Override
    public ResultBean getPersonnersByIds(Integer... ids) {
        log.info("- enter into method PersonnelServiceImpl.getPersonnersByIds,parameter ids:{}", (Object) ids);
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        List<Personnel> personnels = personnelMapper.selectByIds(map);
        if (EmptyUtility.isNullOrEmpty(personnels)) {
            return new ResultBean(1047);
        }
        return new ResultBean<List<Personnel>>(1, personnels);
    }

    /**
     * 新增一个人员
     *
     * @param personnel
     * @return
     */
    @Override
    public ResultBean addPersonnel(Personnel personnel) {
        log.info("- enter into method PersonnelServiceImpl.addPersonnel,parameter personnel:{}", personnel);
        Personnel personnels = personnelMapper.selectByNameEqual(personnel.getName());
        if (!EmptyUtility.isNullOrEmpty(personnels)) {
            return new ResultBean(1048);
        }
        int i = personnelMapper.insert(personnel);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }

    /**
     * 更新一个人员
     *
     * @param personnel
     * @return
     */
    @Override
    public ResultBean putPersonnel(Personnel personnel) {
        log.info("- enter into method PersonnelServiceImpl.putPersonnel,parameter personnel:{}", personnel);
        if (!EmptyUtility.strIsEmpty(personnel.getName())) {
            Personnel personnels = personnelMapper.selectByNameEqual(personnel.getName());
            if (!EmptyUtility.isNullOrEmpty(personnels)) {
                return new ResultBean(1048);
            }
        }
        int i = personnelMapper.updateBySelective(personnel);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }

    /**
     * 删除一个人员
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean delPersonnel(Integer id) {
        log.info("- enter into method PersonnelServiceImpl.delPersonnel,parameter id:{}", id);
        int i = personnelMapper.deleteByPrimaryKey(id);
        if (i < 1) {
            return new ResultBean(0);
        }
        return new ResultBean(1);
    }
}
