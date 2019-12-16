package com.citictel.report.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citictel.report.service.MailService;

/**
 * 
 * @author Vincent Zou
 * 2019/12/15
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

	@Resource
	private MailService mailService;
	
	@GetMapping("/send1")
	public Object getReport() {
		System.out.println("-----send1------");
		 mailService.sendSimpleMail(new String[]{"vincentzou@citictel.com"},"简单文本邮件","这是我的第6封邮件,哈哈...");
		return "OK1";
	}
	
}
