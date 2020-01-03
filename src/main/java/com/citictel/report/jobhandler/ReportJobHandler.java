package com.citictel.report.jobhandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.citictel.report.service.MailService;
import com.citictel.report.service.ReportService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤： 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author vincent zou 2020-01-03 21:43:36
 */
@JobHandler(value = "reportJobHandler")
@Component
public class ReportJobHandler extends IJobHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	ReportService reportService;
	@Resource
	private MailService mailService;

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		String[] strs = param.split("#");
		String datasource = "idd"; //datasource
		if(strs.length > 2)datasource = strs[2];
		if(strs.length < 2) {
			return new ReturnT<String>(500, "input params should be like \"demo1.xlsx?p1=xx&p2=xx2#vincentzou@citictel.com#idd\" ");
		}
		String reportparam = strs[0];
		String emails = strs[1]; //email address

		String[] reportparams = reportparam.split("\\?");
		String _filename = reportparams[0]; //demo1.xlsx
		   String[] __filename = _filename.split("\\.");
		   String name = __filename[0];  //demo1
		   String format = __filename[1]; //xlsx
		Map<String,Object> map = new HashMap<>();//report input paramters
		if(reportparams.length > 1) {
		  String[] rps = reportparams[1].split("&");
		  for (String rp:rps ) {
			System.out.println("rp-->"+rp);
			String[] kv = rp.split("=");
			map.put(kv[0], kv[1]);
		   }
		}
		
		
		XxlJobLogger.log("ReportJobHandler START.........");
		logger.info("Report Engine start------");

		InputStream is =  reportService.generateReport(name, format, map);
		mailService.sendAttachmentMail(emails.split(","), " report--from "+datasource,"This is test for attachment report!", is, _filename);

		logger.info("log===>Report Engine start----");
		XxlJobLogger.log("ReportJobHandler END........");
		return SUCCESS;
	}

	public static void main(String[] args) {
		String link = "demo1.xlsx?p1=xx&p2=xx2#vincentzou@citictel.com#idd";
		String[]  strs = link.split("#");
		String reportparam = strs[0];
		String[] reportparams = reportparam.split("\\?");
		String _reportname = reportparams[0];
		   String[] __filename = _reportname.split("\\.");
		   System.out.println(__filename[0]+" -- "+__filename[1]);
		String _reportparams = reportparams[1];
		Map<String,Object> map = new HashMap<>();
		  String[] rps = _reportparams.split("&");
		  for (String rp:rps ) {
			System.out.println("rp-->"+rp);
			String[] kv = rp.split("=");
			map.put(kv[0], kv[1]);
		   }
		System.out.println(_reportname);
		System.out.println(_reportparams);
		String[] emails =  strs[1].split(",");
		
		System.out.println(reportparam);
		System.out.println(emails[0]);
		
		
	}

}
