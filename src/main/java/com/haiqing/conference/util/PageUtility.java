package com.haiqing.conference.util;

/**
 * Created By haiqing
 * Date: 2019/3/27
 * Time: 16:56
 * 分页工具类
 */
public class PageUtility {
    /**
     * 获取当前页是从几页开始
     * @param page
     * @param size
     * @return
     */
    public static Integer getStart(Integer page,Integer size){
        return (page -1)*size;
    }
}
