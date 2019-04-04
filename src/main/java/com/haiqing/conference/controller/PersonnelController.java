package com.haiqing.conference.controller;

import com.haiqing.conference.dto.PageResultBean;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.pojo.Personnel;
import com.haiqing.conference.service.PersonnelService;
import com.haiqing.conference.util.EmptyUtility;
import com.haiqing.conference.util.PageUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.haiqing.conference.controller.ConferenceController.START_PAGE;
import static com.haiqing.conference.controller.ConferenceController.START_SIZE;

/**
 * 人员管理接口类
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 17:20
 */
@Slf4j
@RestController
public class PersonnelController {
    @Autowired
    private PersonnelService
            personnelService;

    /**
     * 根据id查询人员详情
     * @param id
     * @return
     */
    @GetMapping("/personnel/{id}")
    public ResultBean queryPersonnel(@PathVariable(value = "id")Integer id){
        log.info("- enter into method PersonnelController.queryPersonnel,parameter id:{}",id);
        return personnelService.getPersonner(id);
    }

    /**
     * 查询人员列表信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/personnels")
    public ResultBean queryPersonnels(@RequestParam(name = "page",required = false)Integer page,
                                          @RequestParam(name = "size",required = false)Integer size){
        log.info("- enter into method PersonnelController.queryPersonnels,parameter page:{},size:{}",page,size);
        //参数校验
        if (page == null || page < 1) {
            page = START_PAGE;
        }
        if (size == null || size < 1) {
            size = START_SIZE;
        }
        Integer start = PageUtility.getStart(page,size);
        ResultBean resultBean =personnelService.getPersonners(start,size);
        //加入分页数据
        if (!EmptyUtility.isNullOrEmpty(resultBean.getData())){
            ((PageResultBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }

    /**
     * 根据ids查询人员的信息
     * @param ids 人员id的数组
     * @return
     */
    @GetMapping("/personnelsIds")
    public ResultBean queryPersonnelsIds(@RequestParam(name = "ids")Integer[] ids){
        log.info("- enter into method PersonnelController.queryPersonnelsIds,parameter ids:{}", (Object) ids);
        if (EmptyUtility.isNullOrEmpty(ids)){
            return new ResultBean(1045);
        }
        return personnelService.getPersonnersByIds(ids);
    }

    /**
     * 姓名模糊查询
     * @param name
     * @return
     */
    @GetMapping("/personnelName")
    public ResultBean queryPersonnelByName(@RequestParam(name = "name",required = false)String name){
        log.info("- enter into method PersonnelController.queryPersonnels,parameter name:{}",name);
        if (EmptyUtility.strIsEmpty(name)){
            return new ResultBean(1044);
        }
        return personnelService.getPersonnerByName(name);
    }
    /**
     * 新增一个人员
     * @param personnel
     * @return
     */
    @PostMapping("/personnel")
    public ResultBean createPersonnel(@RequestBody Personnel personnel){
        log.info("- enter into method PersonnelController.createPersonnel,parameter personnel:{}",personnel);
        //参数校验
        if (EmptyUtility.isNullOrEmpty(personnel)){
            return new ResultBean(1001);
        }
        if (EmptyUtility.strIsEmpty(personnel.getName())){
            return new ResultBean(1041);
        }
        if (EmptyUtility.strIsEmpty(personnel.getDepartment())){
            return new ResultBean(1042);
        }
        //加入其它数据
        personnel.setCreateAt(System.currentTimeMillis());
        personnel.setCreateBy(1);
        return personnelService.addPersonnel(personnel);
    }

    /**
     * 更新一个人员
     * @param id
     * @param personnel
     * @return
     */
    @PutMapping("/personnel/{id}")
    public ResultBean putPersonnel(@PathVariable(value = "id")Integer id, @RequestBody Personnel personnel){
        log.info("- enter into method PersonnelController.putPersonnel,parameter id:{id}, personnel:{}",id,personnel);
        //加入更新数据
        personnel.setId(id);
        personnel.setUpdataAt(System.currentTimeMillis());
        personnel.setUpdataBy(1);
        return personnelService.putPersonnel(personnel);
    }

    /**
     * 删除一个人员
     * @param id
     * @return
     */
    @DeleteMapping("/personnel/{id}")
    public ResultBean delPersonnel(@PathVariable(value = "id")Integer id){
        log.info("- enter into method PersonnelController.delPersonnel,parameter id:{}",id);
        return personnelService.delPersonnel(id);

    }

}
