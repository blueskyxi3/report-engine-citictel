package com.citictel.report.jobhandler;

import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.citictel.report.dto.ReportParam;
import com.citictel.report.dto.ReportResult;
import com.citictel.report.service.MailService;
import com.citictel.report.service.ReportService;
import com.citictel.report.utils.ParamtersUtils;
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
@SuppressWarnings("deprecation")
@JobHandler(value = "reportJobHandler")
@Component
public class ReportJobHandler extends IJobHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	ReportService reportService;
	@Resource
	private MailService mailService;

	@Override
	public ReturnT<String> execute(String param) {
		ReportParam rp = JSON.parseObject(param, ReportParam.class);
		XxlJobLogger.log("ReportJobHandler START.........");
		logger.info("Report Engine start------");
		String filenames = rp.getReportName();
		if (StringUtils.isEmpty(filenames))
			return new ReturnT<String>(500, "please set reportname !");
		String[] _filenames = filenames.split(",");
		String filename = _filenames[0];
		String filename1 = null;
		if (_filenames.length > 1)
			filename1 = _filenames[1];
		Map<String, Object> parameters = rp.getParams();
		Map<String, Object> map = ParamtersUtils.handleParameters(parameters);
		InputStream is = null;
		InputStream is1 = null;
		try {
			ReportResult res = reportService.generateReport(filename.split("\\.")[0], filename.split("\\.")[1],
					rp.getDatasource(), map);
			XxlJobLogger.log("ReportJobHandler is have data........" + res.isHaveData());
			if (!res.isHaveData()) {
				XxlJobLogger.log("ReportJobHandler END........");
				return SUCCESS;
			}

			if (StringUtils.isNotEmpty(filename1)) {
				ReportResult res1 = reportService.generateReport(filename1.split("\\.")[0], filename1.split("\\.")[1],
						rp.getDatasource(), map);
				is = res.getInputStream();
				is1 = res1.getInputStream();
				mailService.sendAttachmentMail(rp.getTo(), rp.getCc(), rp.getBcc(), rp.getSubject(), rp.getContent(),
						is, filename, is1, filename1);
			} else {
				is = res.getInputStream();
				mailService.sendAttachmentMail(rp.getTo(), rp.getCc(), rp.getBcc(), rp.getSubject(), rp.getContent(),
						is, rp.getReportName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			XxlJobLogger.log("ReportJobHandler Exception........." + e.getMessage());
			return FAIL;
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(is1);
		}
		logger.info("log===>Report Engine END----");
		XxlJobLogger.log("ReportJobHandler END........");
		return SUCCESS;
	}

}
