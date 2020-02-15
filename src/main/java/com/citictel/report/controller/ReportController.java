package com.citictel.report.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private DataSource dataSource;

	@Resource
	private DataSource secondDatasource;

	@Resource
	private DataSource sqlserverDatasource;

	@Resource
	private DataSource oracleDatasource;
	
	@GetMapping("/{datasource}/{format}/{reportName}")
	public void getReport(@PathVariable("reportName") final String reportName,@PathVariable("datasource")String datasource,@PathVariable("format")String format,
			@RequestParam(required = false) Map<String, Object> parameters, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, JRException, IOException {
		logger.info("datasource===>"+datasource);
		parameters = parameters == null ? new HashMap<>() : parameters;
		// 获取文件流
		ClassPathResource resource = new ClassPathResource("jaspers" + File.separator + reportName + ".jasper");
		InputStream jasperStream = resource.getInputStream();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		Connection connection = null;
		JasperPrint jasperPrint = null;
		try {
			if("local".equals(datasource)) {connection = dataSource.getConnection();}
			else if("IDD" .equals(datasource)) {connection = oracleDatasource.getConnection();}
			else if("Ticket".equals(datasource)) {connection = sqlserverDatasource.getConnection();}
			else if("Second".equals(datasource)) {connection = secondDatasource.getConnection();}
			else  {connection = dataSource.getConnection();}
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			List<JRPrintPage> list = jasperPrint.getPages();
			logger.info("list===>"+list);
			logger.info("list=size==>"+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(jasperStream != null)
				jasperStream.close();
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

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
