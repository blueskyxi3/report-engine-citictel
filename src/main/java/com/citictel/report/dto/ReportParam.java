package com.citictel.report.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReportParam {
	
//	"demo1.xlsx?p1=xx&p2=xx2#vincentzou@citictel.com#idd";

	private String datasource = "idd";
	
	private String reportName ;
	
	private String[] toEmails;
	
	private String[] ccEmails;
	
	private String[] bccEmails;
	
	private String subject ;
	
	private String content ;
	
	private Map<String,Object> params = new HashMap<>();
	

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String[] getCcEmails() {
		return ccEmails;
	}

	public void setCcEmails(String[] ccEmails) {
		this.ccEmails = ccEmails;
	}

	public String[] getBccEmails() {
		return bccEmails;
	}

	public void setBccEmails(String[] bccEmails) {
		this.bccEmails = bccEmails;
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

	public String[] getToEmails() {
		return toEmails;
	}

	public void setToEmails(String[] toEmails) {
		this.toEmails = toEmails;
	}

	@Override
	public String toString() {
		return "ReportParam [datasource=" + datasource + ", reportName=" + reportName + ", toEmails="
				+ Arrays.toString(toEmails) + ", ccEmails=" + Arrays.toString(ccEmails) + ", bccEmails="
				+ Arrays.toString(bccEmails) + ", title=" + subject + ", content=" + content + ", params=" + params + "]";
	}


	
	
}
