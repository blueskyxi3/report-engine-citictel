package com.citictel.report.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

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
 * @author Vincent Zou 2020/01/03
 */
@Service
public class ReportService {

	@Resource
	private DataSource dataSource;

	@Resource
	private DataSource secondDatasource;

	public InputStream generateReport(String reportName, String format, Map<String, Object> parameters)
			throws JRException, IOException {

		parameters = parameters == null ? new HashMap<>() : parameters;
		// 获取文件流
		ClassPathResource resource = new ClassPathResource("jaspers" + File.separator + reportName + ".jasper");
		InputStream jasperStream = resource.getInputStream();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		Connection connection = null;
		JasperPrint jasperPrint = null;
		try {
			connection = dataSource.getConnection();
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		if (null == format) format = "html";
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		switch (format) {
		case "docx":
			JRDocxExporter worldExporter = new JRDocxExporter();
			worldExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			worldExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			worldExporter.exportReport();
			break;
		case "xlsx":
			JRXlsxExporter xlsxExporter = new JRXlsxExporter();
			xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			xlsxExporter.exportReport();
			break;
		case "pdf":
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			pdfExporter.exportReport();
			break;
		default:
			HtmlExporter exporter = new HtmlExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(byteArrayOutputStream));
			exporter.exportReport();
			break;
		}
		InputStream is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return is; 
	}
}
