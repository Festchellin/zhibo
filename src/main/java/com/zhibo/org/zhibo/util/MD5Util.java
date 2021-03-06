package com.zhibo.org.zhibo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author LS
 * @date 2018/9/17
 * 将字符串使用MD5算法进行加密
 */
public class MD5Util {

    public static String getMD5Str(String str){
        try {
            //生成一个MD5加密计算摘要
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //使用MD5计算函数
            md5.update(str.getBytes());

            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("MD5加密出错！！");
        }
        return str;
    }

}
