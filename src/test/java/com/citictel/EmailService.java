package com.citictel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    //简单邮件测试
    public void sendSimple(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("this is good msg email1");
        message.setText("mail for 1111111");
        message.setTo("vincentzou@citictel.com");
      //  message.setCc("zouwei.2006@163.com");
        message.setFrom("zouwei.2006@163.com");
        javaMailSender.send(message);
        System.out.println(javaMailSender);
    }
    //复杂邮件测试
    public void sendComplicated() throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //用MimeMessageHelper来包装MimeMessage
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("email测试");
        mimeMessageHelper.setText("邮件测试内容");
        mimeMessageHelper.setTo("343854933@qq.com");
        mimeMessageHelper.setFrom("zouwei.2006@163.com");
        mimeMessageHelper.addAttachment("test.sh",new File("/Users/zouw/test.sh"));
        javaMailSender.send(mimeMessage);

    }
}