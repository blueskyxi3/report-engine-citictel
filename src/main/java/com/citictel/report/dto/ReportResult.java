package com.citictel.report.dto;

import java.io.InputStream;

public class ReportResult {
	
	private InputStream inputStream = null;
	
	private boolean isHaveData = false;

	
	public ReportResult(InputStream inputStream, boolean isHaveData) {
		super();
		this.inputStream = inputStream;
		this.isHaveData = isHaveData;
	}

	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}



	public boolean isHaveData() {
		return isHaveData;
	}

	public void setHaveData(boolean isHaveData) {
		this.isHaveData = isHaveData;
	}

	
}
