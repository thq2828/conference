package com.haiqing.conference.exception.exceptionHandling;

import com.haiqing.conference.dao.PersonnelMapper;
import com.haiqing.conference.dto.ResultBean;
import com.haiqing.conference.exception.IsConferenceException;
import com.haiqing.conference.pojo.Personnel;
import com.haiqing.conference.service.ConferenceService;
import com.haiqing.conference.service.PersonnelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By haiqing
 * Date: 2019/4/1
 * Time: 14:25
 * 该类室全局异常处理类，用于捕捉异常进行处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private PersonnelMapper personnelMapper;
    @ExceptionHandler(value = IsConferenceException.class)
    @ResponseBody
    public ResultBean IsConferenceExceptionHandler(IsConferenceException i)throws Exception {
        log.error(i.getLocalizedMessage()+"人员id:"+i.getPersonnelId());
        //查询出id对应的人员信息
         Personnel personnel =personnelMapper.selectByPrimaryKey(i.getPersonnelId());
        return new ResultBean<Personnel>(10000,i.getMessage(),personnel);
    }
    //@ExceptionHandler(value = Exception.class)
    //@ResponseBody
    //public ResultBean ExceptionHandler(Exception e){
    //    log.error(e.getMessage());
    //    return new ResultBean(10001);
    //}
}
