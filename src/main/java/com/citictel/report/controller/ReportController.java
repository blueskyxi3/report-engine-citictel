package com.citictel.report.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * 
 * @author Vincent Zou
 * 2019/12/15
 */
@RestController
@Api(tags = "ReportController", description = "Report Engine Interface")
@RequestMapping("/report")
public class ReportController {

	@Resource
	private DataSource dataSource;
	
	@Resource
	private DataSource secondDatasource;
	
	@GetMapping("/{format}/{reportName}")
	public void getReport(@PathVariable("reportName") final String reportName,@PathVariable("format")String format,
			@RequestParam(required = false) Map<String, Object> parameters, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, JRException, IOException {

		parameters = parameters == null ? new HashMap<>() : parameters;
		// 获取文件流
		ClassPathResource resource = new ClassPathResource("jaspers" + File.separator + reportName + ".jasper");
		InputStream jasperStream = resource.getInputStream();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

		response.setCharacterEncoding("utf-8");
		if(null == format)format = "html";
		String fileName = reportName + "." + format;
		
		switch (format) {
		case "docx":
			response.setHeader("Content-Disposition",
					"attachment;" + "filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
			//response.addHeader("Content-Type", "application/x-msword");
			response.setContentType("application/x-msword");
			JRDocxExporter worldExporter = new JRDocxExporter();
			worldExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			worldExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
			worldExporter.exportReport();
			break;
		case "xlsx":
			response.setHeader("Content-Disposition",
					"attachment;" + "filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
			response.setContentType("application/vnd_ms-excel");
			JRXlsxExporter xlsxExporter = new JRXlsxExporter();
			xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
			xlsxExporter.exportReport();
			break;
		case "pdf":
			response.setHeader("Content-Disposition",
					"attachment;" + "filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
			response.setContentType("application/pdf");
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
			pdfExporter.exportReport();
			break;
		default:
			HtmlExporter exporter = new HtmlExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
			exporter.exportReport();
			break;
		}
		
	}
}
