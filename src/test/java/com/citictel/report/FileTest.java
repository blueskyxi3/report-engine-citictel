package com.citictel.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class FileTest {

	@Test
	public void test() {
		File file = new File("C:\\Users\\vincentzou\\JaspersoftWorkspace\\MyReports\\Citiltel_Landscape_1.jasper");
		getModifiedTime_2(file);
	}

	public static void getModifiedTime_2(File f) {

		Calendar cal = Calendar.getInstance();
		long time = f.lastModified();
		System.out.println(time);// 1578279659020
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.setTimeInMillis(time);
		System.out.println("修改时间[2] " + formatter.format(cal.getTime()));
		// 输出：修改时间[2] 2009-08-17 10:32:38
	}

	@Test
	public void test2() {
		 String sourceFileName = "C:\\Users\\vincentzou\\JaspersoftWorkspace\\MyReports\\Citiltel_Landscape_1.jrxml";

			      System.out.println("Compiling Report Design ...");
			      try {
			         /**
			          * Compile the report to a file name same as
			          * the JRXML file name
			          */
			         JasperCompileManager.compileReportToFile(sourceFileName);
			      } catch (JRException e) {
			         e.printStackTrace();
			      }
			      System.out.println("Done compiling!!! ...");//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/jasper_reports/jasper_compiling_report_design.html

	}

}
