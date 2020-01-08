package com.citictel.report.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReportParam {
	
//	"demo1.xlsx?p1=xx&p2=xx2#vincentzou@citictel.com#idd";

	private String datasource = "idd";
	
	private String reportName ;
	
	private String[] to;
	
	private String[] cc;
	
	private String[] bcc;
	
	private String subject ;
	
	private String content ;
	
	private Map<String,Object> params = new HashMap<>();
	

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}



	@Override
	public String toString() {
		return "ReportParam [datasource=" + datasource + ", reportName=" + reportName + ", to="
				+ Arrays.toString(to) + ", cc=" + Arrays.toString(cc) + ", bcc="
				+ Arrays.toString(bcc) + ", title=" + subject + ", content=" + content + ", params=" + params + "]";
	}


	
	
}
