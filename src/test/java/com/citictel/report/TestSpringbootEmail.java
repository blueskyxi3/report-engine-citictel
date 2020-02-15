package com.citictel.report;


import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.citictel.report.service.MailService;
import com.citictel.report.service.ReportService;

import net.sf.jasperreports.engine.JRException;

/**
 * Created by ${ligh} on 2018/12/7 上午9:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringbootEmail {

    @Resource
    MailService mailService;
    
    @Resource
    ReportService reportService;

    @Resource
    TemplateEngine templateEngine;

    
    /**
     *  简单文本邮件发送
     * @throws IOException 
     * @throws JRException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void sendReportTest() throws ClassNotFoundException, SQLException, JRException, IOException{
    	System.out.println("----------report---------");
    	reportService.generateReport("demo1", "xlsx", "",null);
    }
    @Test
    public void jsonTest() throws ClassNotFoundException, SQLException, JRException, IOException{
    	System.out.println("----------report---------");
    	reportService.generateReport("demo1", "xlsx","", null);
    }
    @Test
    public void test3() 
    {
        //简单对象类型
        String jsonStr = "{'name':'Misaya','position':'Mid','hero':':Twisted Fate'}";
        
        JSONObject json = JSON.parseObject(jsonStr);
        String name = json.getString("name");
        System.out.println(name);
        
        System.out.println(json);
    }
    /**
     *  简单文本邮件发送
     */
    @Test
    public void sendSimpleMailTest(){
    	System.out.println("----------simple---------");
        mailService.sendSimpleMail(new String[]{"vincentzou@citictel.com"},"简单文本邮件","这是我的第5封邮件,哈哈...");
    }

    @Test
    public void sendHtmlMailTest() throws Exception{

        String content = "<html>\n"+
                        "<body>\n" +
                            "<h1 style=\"color: red\"> hello world , 这是一封HTML邮件</h1>"+
                        "</body>\n"+
                        "</html>";
        mailService.sendHtmlMail(new String[]{"vincentzou@citictel.com"},"Html邮件发送",content);
    }
    
    @Test
    public void sendAttachmentMailTest() throws Exception{
        String filepath = "/Users/zouw/test.sh";
        mailService.sendAttachmentMail(new String[]{"vincentzou@citictel.com"},"发送副本","这是一篇带附件的邮件",filepath);
    }
    
    @Test
    public void sendImageMailTest() throws Exception{
        //发送多个图片的话可以定义多个 rscId,定义多个img标签

        String filePath = "/Users/zouw/11.png";
        String rscId = "ligh001";
        String content = "<html><body> 这是有图片的邮件: <img src=\'cid:"+rscId+"\'> </img></body></html>";
        mailService.sendImageMail(new String[]{"vincentzou@citictel.com"},"这是一个带图片的邮件",content,filePath,rscId);
    }
    
    @Test
    public void sendTemplateEmailTest() throws Exception {
        Context context = new Context();
        context.setVariable("id","008");
        String emailContent = templateEngine.process("templates2",context);
        mailService.sendHtmlMail(new String[]{"vincentzou@citictel.com"},"这是一个模板文件",emailContent);
    }
}
