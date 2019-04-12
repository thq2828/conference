package com.haiqing.conference.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created By haiqing
 * Date: 2019/4/11
 * Time: 9:40
 */
public class JsonUtil {
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, Class<T> cla) {
        return JSON.parseObject(json, cla);
    }

    public static <T> List<T> toList(String json, Class<T> t) {
        return JSON.parseArray(json, t);
    }
}
