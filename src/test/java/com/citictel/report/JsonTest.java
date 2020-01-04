package com.citictel.report;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.citictel.report.dto.ReportParam;

public class JsonTest {

	 @Test
	    public void Json2Obj() 
	    {
	        //待解析的json字符串
	        String jsonString = "{'name':'卢本伟','age':24,'Hero':{'name':'Fizz','Position':'Mid','charactor':'killer'},"
	        		+ "'nickNames':['五五开','芦苇','white'],'Honors':[{'year':2011,'name':'TGA总决赛冠军'},"
	        		+ "{'year':2013,'name':'S3全球总决赛中国区冠军'},{'year':2013,'name':'S3全球总决赛亚军'}]}";
	        
	        Player p = JSON.parseObject(jsonString, Player.class);
	        
	        System.out.println(p);
	        
	    }

//		"demo1.xlsx?p1=xx&p2=xx2#vincentzou@citictel.com#idd";
	 @Test
	    public void Json2RepoartParam() 
	    {
	        //待解析的json字符串
	        String jsonString = "{'reportName':'demo1.xlsx','datasource':'idd','params':{'p1':'Fizz','p2':'Mid','p3':'killer'},"
	        		+ "'toEmails':['vincentzou@citictel.com','343854933@qq.com'],"
	        		+ "'ccEmails':['vincentzou1@citictel.com','343854932@qq.com'],"
	        		+ "'bccEmails':['vincentzoubxx@citictel.com','343854931@qq.com'],"
	        		+ "'subject':'this is test email report',"
	        		+ "'content':'Dir Sir, /n this is test email report. please refer to attachemnt for detail info! /n thanks.'}";
	        
	        ReportParam p = JSON.parseObject(jsonString, ReportParam.class);
	        
	        System.out.println(p);
	        
	    }
}
