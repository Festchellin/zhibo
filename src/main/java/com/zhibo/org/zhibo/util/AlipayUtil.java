package com.zhibo.org.zhibo.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.zhibo.org.zhibo.entity.Order;
import com.zhibo.org.zhibo.entity.Product;
import org.junit.Test;


public class AlipayUtil {
    private static String URL = "https://openapi.alipaydev.com/gateway.do";
    private static String APP_ID = "2016091600525916";
    private static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCWPZuG3D/UB0JDohctBdrNuZxaNrAQqbjVs7e27MwNtjDxjnmk2ammQNI2zp5rUUNeWcx74RoZ0AP+BeJZdFqTUTsNP3Is6P4tIr+VDwQ+LQqxHntxhwCLGQrRzfXZCf9SXBgLJix/IArhvFLdftFEo+2/BscaZw9WGT64GXNoZAbuPzT2+Kdgl+ZF4EuujcHBzpXyXtnvTJXlulGoAmfg6uUbkB8qu6Kg3pHshg3dUE/PdQDCztYXFuhBg9qTsQjexB61TEsXdm1ASR7xKR4bKF3pAu5f1AILuSY5/Y4fxoSYT/a5Evz7RdmdU7iZy79c3bXn4mHCBd7DQUivXT//AgMBAAECggEBAIqXumjltFu7dn09gXOe+wDcQEdYO4KjjkZUyxyLxJDBiNTMQZq4z672DPVSFQGarTLUU7XTKWi2Y3hppSwwEX3kaT0HUiPrqs2oSGMQvzE8C6joHafUicPHDhwMisX0Wvt2yRc/17xNqrwiJa791b4kGqUlc3X/rD4Ye6sIj1WzxnSG4+OrFacLfYiRq5RpdU+xM2Y1o9c79grJfdBFMU7la6iNc7OHCEGH9nPftP/lwLXcVHyixzd7OYa5qU3lCo9f9kWoiu+NadgCwTOCGXcFJlND+WUZax6i4YLg2qL4KEf0nA1J84/Q0pu1inMoTSJ4XzkxtDYeaTenqM7rIkECgYEAxr99dAIbTbycNnFsvY52rVN4aDQjYkw2B9/+WdD/DcD9WEvfNG86aH4VeovborxqKkmoM/HzecqIGEcdMtiVuc6yESbylSmE8Mrw9D5tpSAD+6hTRifVtteL1xWNi+3UkfF8GWz2VXYlVmcVgI0y5VcOxwe+RCrTPFK9InGUz9UCgYEAwYT7cU2xCPBvqJAx4ttnBQM6N86FrkIaMOcldH7jozYn6Ejk7qhrzTBNEmGwlIbJNsi3ps2S85RDQ9NLc+cVpk7u966sTCsn0TFesx+pxLBZnTHoExeynTeSsvM9X+nYbPFveSb1Y31ifhPTE8yPXXu0+h2fMpgrSUlRb2tUToMCfx/Ui0MJE1Zf3qTZhZDWQCuCMsZ9ky0rB3w7Y4D+99o8DlcVz6mWJsSVbNszivmFM3uSUF4BYf0fcqxwtzxtc41OjTYyBqfc/m2SEwUK+oR3nCuxyaRyvMeLvdm0mfbPIDw/cKCh0tHdGh28ZLUx5/TS/IrjspU4Rpxio+fgSPUCgYEAuHIHNfbWxa5kvkSxR4fWqPBqjMZKo8xdKj8lc+N9FBKJ9tuDV/UuymM/EkYKB4ZwQccNQ+6sIX0un3bEc7PM7rZNDcVucyquyLRFjnSfWti6kRkAGTJ9C1vsdqNf3AsGPM/hb5eySyE2vvQmOri97Fv8ImgZVB+1IiS/eXLS6AECgYBALPDcNPJk3nF+uCULDnnRoG0z44s4JqeyGnbNZDhL2/cQMK3vNnzY2dwd2NnYbWbNCURnCnotP9QCb7moTgEi5L+ZOz5SYjvgIwdjh8JJEuuCqT9xqzjl/CwMXHZDA0G53v4Diuey9n3HfaaZocLLza0jEKEP5WYDOtfHP3cJpQ==";
    private static String FORMAT = "json";
    private static String CHARSET = "UTF-8";
    private static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqEgS9jKNxyosyIvD/e9NAlsbyU0JMTdIm3ZzCbClmrRtb2uqrvK23QtJj8SzMe1EZkuv65bFoJnnq2oC0EgtoteZ4XDO6zQrXdmvSAry9b6U3xALKe6Cgd+7jF+qpLXGt5HRaSPDcjV/3ZCnGmru/Q7JA//XFolVO7/ZUvQb4o57zHmvpgRS6IWB5LbycQIkhiAlN12/Wx4jFEHweuxjLclNIGFB1dWujVBKxhj2zoBBN8alpITo12hnvz6MW+cowA0SL+A9uyrgT1kNn/8XNbewrG54f0q/nN27o4J1ovTQtcA9XwdV2qdfRcfUNFqWmINcHXntFJiBWmzcWPvLRQIDAQAB";
    private static String SIGN_TYPE = "RSA2";

    public static AlipayClient getAlipayClient() {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        return alipayClient;
    }

    public static AlipayTradePagePayRequest formatAlipayPageRequest(String returnURL, String data) {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizContent(data);
        request.setReturnUrl(returnURL);
        return request;
    }

    public static AlipayFundTransToaccountTransferRequest formatFundTransToAccountTransferRequest(String payeeAccount, Double amount) {
        String data = StringGenerator.alipayFundTransToaccountTransferRequestBizContentGenerator(payeeAccount, amount);
//        System.out.println(data);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent(data);
        return request;
    }

    @Test
    public void test() {
        Product product = new Product();
        product.setPrice(6999.99);
        product.setProductName("iphone xr");
        product.setBody("iPhone XR是美国Apple（苹果公司）旗下的智能手机，搭载7nm工艺的A12仿生芯片，采用TrueDepth摄像头，支持Face ID功能。 2018年9月13日凌晨，iPhone XR在2018苹果秋季新品发布会上正式发布，起售价749美元。 [1]  2018年10月19日开启预购，2018年10月26日正式发售。");
        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(3);
        order.setSubject("商品名称：" + product.getProductName() + "，数量：" + order.getQuantity());
        String json = StringGenerator.alipayTradeRequestBizContentGenerator(order);
        AlipayClient alipayClient = getAlipayClient();
        AlipayTradePagePayRequest request = formatAlipayPageRequest("http://localhost:8080/user/center", json);
        try {
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                System.out.println("支付成功");
                System.out.println(response.getBody());
            } else
                System.out.println("支付失败");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTransfer() {
        AlipayFundTransToaccountTransferRequest request = formatFundTransToAccountTransferRequest("smsxcb1435@sandbox.com", 100.01);
        AlipayClient alipayClient = getAlipayClient();
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                System.out.println("转账成功");
                System.out.println(response.getBody());
            } else
                System.out.println("转账失败");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
