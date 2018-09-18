package com.zhibo.org.zhibo.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 发送激活邮件工具类
 * @author LS
 * @date 2018/9/17
 */
public class MailUtil implements Runnable {
    //收件人邮箱
    private String email;
    //激活码
    private String code;
    //授权码
    private String authCode="";

    public MailUtil(String email,String code){
        this.email = email;
        this.code = code;
    }

    @Override
    public void run() {
        //发件人电子邮箱
        String from = "XX@qq.com";
        //指定发送邮件的主机
        String host = "smtp.qq.com";

        //获取系统属性
        Properties properties = System.getProperties();

        //设置邮件服务器
        properties.setProperty("mail.smtp.host",host);
        //打开认证
        properties.setProperty("mail.smtp.auth","true");

        try {
            //QQ邮箱需要以下代码
            MailSSLSocketFactory sslSocketFactory = new MailSSLSocketFactory();
            sslSocketFactory.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.ssl.socketFactory",sslSocketFactory);

            //1、获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮箱账号、授权码
                    return new PasswordAuthentication(from,authCode);
                }
            });

            //2.创建邮箱对象
            MimeMessage message = new MimeMessage(session);
            //2.1 设置发件人
            message.setFrom(from);
            //2.2 设置接收人
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            //2.3 设置邮件主题
            message.setSubject("账号激活");
            //2.4 设置邮件内容
            String content = "<html><head></head><body><h1>这是一封激活邮件,如果你不了解，请忽视这封邮件；如果你已经了解，激活请点击以下链接</h1><h3><a href='http://localhost:8080/mailActivation?code="
                    + code + "'>http://localhost:8080/mailActivation?code=" + code
                    + "</href></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
