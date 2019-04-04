package com.haiqing.conference.dto;

/**
 * 状态码枚举类，主要封装操作的一些状态码和信息
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 15:07
 */
public enum StatusCode {
    /**
     * 公共statusCode
     */
    SUCCESS(1,"操作成功"),
    FALIED(0,"操作失败"),
    OBJECT_NULL(1001, "输入的值为空！"),
    EXCEPTION(10001,"未知错误"),
    /**
     * 会议管理状态码
     */
    MEETTINGID_NULL(1021, "会议室不能为空"),
    PERSONNELID_NULL(1022, "组织人员不能为空"),
    TITLE_NULL(1023, "标题不能为空"),
    TIME_NULL(1024, "时间不能为空"),
    TIME_ERROR(1025, "结束时间不能小于开始时间"),
    CONFEREE_ERROR(1026, "参会人员必须大于一位"),
    CONFERENCE_NULL(1027,"没有该条数据"),
    CONFERENCES_NULL(1028,"没有搜索到该条件的数据"),
    PERSONNELID_CONFLICT(1029,"组织人员在这时间段有组织的会议,请选别的时间段"),
    MEETTING_CONFILICT(1030,"会议室在这段时间被占用，请选择别的会议室或者换时间段"),
    UPDATE_PERSONNEL_ERROR(1031,"新增会议成功但是更新参会人员失败"),
    /**
     * 会议室管理状态码
     */
    NAME_NULL(1036,"会议室名字不能为空"),
    ADDRESS_NULL(1037,"会议室地址不能为空"),
    MEETTINGNAME_EXIST(1038,"这个会议室名字已存在"),
    MEETTINGROOM_NULL(1039,"没有该会议室数据"),
    MEETTINGROOMS_NULL(1040,"没有会议室数据"),

    /**
     * 人员管理状态码
     */
    PERSONNEL_NAME_NULL(1041,"姓名不能为空"),
    DEPARTMENT_NULL(1042,"部门不能为空"),
    MEETTING_NAME_NULL(1043,"搜索会议室名字不能为空"),
    PERSONNELNAME_NULL(1044,"人员名字不能为空"),
    IDS_NULL(1045,"参会人员不能为空"),
    PERSONNEL_NULL(1046,"没有该人员信息"),
    PERSONNELS_NULL(1047,"没有人员数据信息"),
    PERSONNEL_NAME_ERROR(1048,"该人员的名字已经被占用")
    ;
    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getName(int index) {
        for (StatusCode c : StatusCode.values()) {
            if (c.getCode() == index) {
                return c.message;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) {
        System.out.println(StatusCode.getName(1));
    }

}