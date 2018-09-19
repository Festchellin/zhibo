package com.zhibo.org.zhibo.util;

import com.google.gson.Gson;
import com.zhibo.org.zhibo.entity.Order;
import com.zhibo.org.zhibo.entity.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author dream
 * @date 2018/09/17
 * 字符串 生成工具类
 */
public class StringGenerator {
    /**
     * 生成默认随机UUID
     *
     * @return
     */
    public static String UUIDGenerator(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }

    /**
     * 根据订单生成支付宝支付参数
     *
     * @param order 订单
     * @return
     */
    public static String alipayTradeRequestBizContentGenerator(Order order) {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();
        Product product = order.getProduct();
        data.put("out_trade_no", System.currentTimeMillis());
        data.put("product_code", "FAST_INSTANT_TRADE_PAY");
        data.put("total_amount", product.getPrice() * order.getQuantity());
        data.put("subject", order.getSubject());
        data.put("body", product.getBody());
        return gson.toJson(data);
    }

    public static String alipayFundTransToaccountTransferRequestBizContentGenerator(String payeeAccount, Double amount) {
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<>();
        data.put("out_biz_no", System.currentTimeMillis());
        data.put("payee_type", "ALIPAY_LOGONID");
        data.put("payee_account", payeeAccount);
        data.put("amount", amount);
        data.put("remark", "收益体现已到账，请查收");
        return gson.toJson(data);
    }
}
