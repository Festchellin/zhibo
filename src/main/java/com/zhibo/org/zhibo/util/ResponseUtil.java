package com.zhibo.org.zhibo.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Map loadResponseWithData (String errorCode,String msg,Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("error_code",errorCode);
        map.put("message",msg);
        map.put("data",data);
        return map;
    }
    public static Map loadResponseWithoutData (String errorCode,String msg){
        Map<String,Object> map = new HashMap<>();
        map.put("error_code",errorCode);
        map.put("message",msg);
        return map;
    }
}
