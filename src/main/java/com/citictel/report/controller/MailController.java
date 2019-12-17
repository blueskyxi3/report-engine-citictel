package com.citictel.report.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value = "/simple/{title}/{recipients}", method = RequestMethod.POST)
	public Object getReport(String title,String[] recipients,@RequestBody String content) {
		mailService.sendSimpleMail(recipients,title,content);
		return "SUCCESS";
	}
	
}
