package com.citictel.report.jobhandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.citictel.report.service.MailService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 跨平台Http任务
 *
 * @author xuxueli 2018-09-16 03:48:34
 */
@JobHandler(value = "httpJobHandler")
@Component
public class HttpJobHandler extends IJobHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MailService mailService;

	@Value("${httpclient.connecttimeout}")
	private int connectTimeout;

	@Value("${httpclient.readtimeout}")
	private int readTimeout;

	public int  getTimeout(String str) {
		if(str.contains("timeout=")) {
			String substring = str.substring(str.indexOf("timeout=")+8);
			return Integer.parseInt(substring);
		}
		return readTimeout;
	}
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		XxlJobLogger.log("XXL-JOB, HttpJobHandler START.");
		XxlJobLogger.log("--http---START----param:{}", param);
		int timeout = getTimeout(param);
		
		XxlJobLogger.log("connectTimeout:{},readTimeout:{}", connectTimeout,timeout);
		logger.info("--http---START----param:{}", param);
		// request
		HttpURLConnection connection = null;
		BufferedReader bufferedReader = null;
		try {
			// connection
			URL realUrl = new URL(param);
			connection = (HttpURLConnection) realUrl.openConnection();

			// connection setting
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setReadTimeout(timeout);
			connection.setConnectTimeout(connectTimeout);
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");

			// do connection
			connection.connect();
			// Map<String, List<String>> map = connection.getHeaderFields();

			// valid StatusCode
			int statusCode = connection.getResponseCode();
			if (statusCode != 200) {
				throw new RuntimeException("Http Request StatusCode(" + statusCode + ") Invalid.");
			}

			// result
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			String responseMsg = result.toString();

			XxlJobLogger.log(responseMsg);
			return ReturnT.SUCCESS;
		} catch (Exception e) {
			XxlJobLogger.log(e);
			return ReturnT.FAIL;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e2) {
				XxlJobLogger.log(e2);
			}
		}

	}

}
