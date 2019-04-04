package com.haiqing.conference.dto;

/**
 * 统一返回前端的结果bean
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 14:54
 */
public class ResultBean<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultBean() {
    }

    public ResultBean(Integer code) {
        this.code = code;
        this.message = StatusCode.getName(code);
    }

    public ResultBean(Integer code, T data) {
        this.code = code;
        this.message = StatusCode.getName(code);
        this.data = data;
    }

    public ResultBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultBean(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
