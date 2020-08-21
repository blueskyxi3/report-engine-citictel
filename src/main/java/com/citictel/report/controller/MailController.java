package com.citictel.report.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.citictel.report.dto.ReportParam;
import com.citictel.report.jobhandler.ReportJobHandler;
import com.citictel.report.service.MailService;

import io.swagger.annotations.Api;

/**
 * 
 * @author Vincent Zou
 * 2019/12/15
 */
@RestController
@RequestMapping("/mail")
@Api(tags = "MailController", description = "E-Mail Interface")
public class MailController {

	@Resource
	private MailService mailService;
	
	@Autowired
	ReportJobHandler reportJobHandler;
	
	@RequestMapping(value = "/simple/{title}/{recipients}", method = RequestMethod.POST)
	public Object sentEmail(String title,String[] recipients,@RequestBody String content) {
		mailService.sendSimpleMail(recipients,title,content);
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/html/{title}/{recipients}", method = RequestMethod.POST)
	public Object sentHtmlMail(String title,String[] recipients,@RequestBody String content) {
		try {
			mailService.sendHtmlMail(recipients,title,content);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";	
		}
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/simple/report", method = RequestMethod.GET)
	public Object sentEmail() {
		String param = "{\"reportName\":\"DCO_Monthly_Provisioning.html\",\"datasource\":\"CDR\",\"params\":{\"startDate\":\"$M-1-S\",\"endDate\":\"$M-1-E\"},\"to\":[\"vincentzou@citictel.com\"],\"cc\":[\"alexhe@citictel.com\"],\"subject\":\"IDC-BSS DCO Monthly Provisioning Records\",\"content\":\"Dir sir,n Attached the last month IDC-BSS DCO Monthly Provisioning Records.\"}";
		reportJobHandler.execute(param);
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/simple/report1", method = RequestMethod.GET)
	public Object sentEmailWithAttachment() {
		String param = "{\"reportName\":\"DCO_Monthly_Provisioning.html,DCO_Monthly_Provisioning.xlsx\",\"datasource\":\"CDR\",\"params\":{\"startDate\":\"$M-1-S\",\"endDate\":\"$M-1-E\"},\"to\":[\"vincentzou@citictel.com\"],\"cc\":[\"leozou@citictel.com\"],\"subject\":\"IDC-BSS DCO Monthly Provisioning Records\",\"content\":\"Dir sir,n Attached the last month IDC-BSS DCO Monthly Provisioning Records.\"}";
		reportJobHandler.execute(param);
		return "SUCCESS";
	}
	
	public static void main(String[] args) {
		String param = "{\"reportName\":\"DCO_Monthly_Provisioning.html\",\"datasource\":\"CDR\",\"params\":{\"startDate\":\"$M-1-S\",\"endDate\":\"$M-1-E\"},\"to\":[\"vincentzou@citictel.com\"],\"cc\":[\"alexhe@citictel.com\"],\"subject\":\"IDC-BSS DCO Monthly Provisioning Records\",\"content\":\"Dir sir,n Attached the last month IDC-BSS DCO Monthly Provisioning Records.\"}";
		ReportParam rp = JSON.parseObject(param, ReportParam.class);
	}
}
