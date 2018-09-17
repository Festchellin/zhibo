package com.zhibo.org.zhibo.util;

import java.util.UUID;

/**
 * @author dream
 * @date 2018/09/17
 */
public class StringGenerator {
    public static String UUIDGenerator(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }
}
