package com.haiqing.conference.exception;

/**
 * 是否参会人员冲突的运行时异常类
 * Created By haiqing
 * Date: 2019/3/28
 * Time: 17:26
 */
public class IsConferenceException extends RuntimeException {
    private Integer personnelId;
    public IsConferenceException() {
    }

    public IsConferenceException(String message) {
        super(message);
    }

    public IsConferenceException(String message,Integer personnelId) {
        super(message);
        this.personnelId = personnelId;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }
}
