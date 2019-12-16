/*
 * package com.citictel; import java.io.File; import java.io.IOException; import
 * java.util.Map;
 * 
 * import javax.servlet.http.HttpServletResponse;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory;
 * 
 * import net.sf.jasperreports.engine.JRException; import
 * net.sf.jasperreports.engine.JasperFillManager; import
 * net.sf.jasperreports.engine.JasperPrint; import
 * net.sf.jasperreports.engine.JasperPrintManager; import
 * net.sf.jasperreports.engine.JasperReport; import
 * net.sf.jasperreports.engine.JasperReportsContext; import
 * net.sf.jasperreports.engine.SimpleJasperReportsContext; import
 * net.sf.jasperreports.engine.export.HtmlExporter; import
 * net.sf.jasperreports.engine.export.JRPdfExporter; import
 * net.sf.jasperreports.engine.export.ooxml.JRDocxExporter; import
 * net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter; import
 * net.sf.jasperreports.engine.util.JRLoader; import
 * net.sf.jasperreports.export.DocxReportConfiguration; import
 * net.sf.jasperreports.export.PdfReportConfiguration; import
 * net.sf.jasperreports.export.SimpleExporterInput; import
 * net.sf.jasperreports.export.SimpleHtmlExporterConfiguration; import
 * net.sf.jasperreports.export.SimpleHtmlExporterOutput; import
 * net.sf.jasperreports.export.SimpleOutputStreamExporterOutput; import
 * net.sf.jasperreports.export.XlsxReportConfiguration;
 * 
 * public class UtilReport {
 * 
 * private static final Logger LOG = LoggerFactory.getLogger(UtilReport.class);
 * 
 *//**
	 * 报表文件导出(PDF,Excle,World)
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 模板文件的绝对路径
	 * <p>
	 * <p>
	 * @param fileName 输出文件名
	 * <p>
	 * <p>
	 * @param suffix 文件后缀名
	 * <p>
	 * 
	 * @throws JRException +--+-************
	 * @throws IOException
	 */
/*
 * public static void reportExport(HttpServletResponse response, Map<String,
 * Object> params, String filePath, String fileName, String suffix) throws
 * JRException, IOException{ try { switch (suffix){ case "PDF":
 * pdfExport(response, params, filePath, fileName, suffix, null); break; case
 * "WORLD": worldExport(response, params, filePath, fileName, suffix, null);
 * break; case "EXCEL": excleExport(response, params, filePath, fileName,
 * suffix, null); break; default: LOG.error("不支持导出此类文件!"); throw new
 * JRException("不支持导出此类文件!"); } } catch (JRException e) { throw e; } catch
 * (IOException e) { throw e; }
 * 
 * }
 * 
 *//**
	 * pdf导出方法
	 * <p>
	 * @param response
	 * <p>
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 模板文件的绝对路径
	 * <p>
	 * <p>
	 * @param fileName 输出文件名
	 * <p>
	 * <p>
	 * @param suffix 文件后缀名
	 * <p>
	 * <p>
	 * @param pr 输出配置信息
	 * <p>
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
/*
 * public static void pdfExport(HttpServletResponse response, Map<String,
 * Object> params, String filePath, String fileName, String suffix,
 * PdfReportConfiguration pr) throws JRException, IOException{ JasperPrint
 * jasperPrint; try { File jasperFile = new File(filePath); JasperReport
 * jasperReport = (JasperReport) JRLoader.loadObject(jasperFile); jasperPrint =
 * JasperFillManager.fillReport(jasperReport, params,null);
 * response.setCharacterEncoding("utf-8"); fileName = fileName + "." +
 * suffix.toString(); response.setHeader("Content-Disposition","attachment;" +
 * "filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
 * response.setContentType("application/pdf"); JRPdfExporter pdfExporter = new
 * JRPdfExporter(); pdfExporter.setExporterInput(new
 * SimpleExporterInput(jasperPrint)); pdfExporter.setExporterOutput(new
 * SimpleOutputStreamExporterOutput(response.getOutputStream()));
 * if(!UvUtil.isNull(pr)){ pdfExporter.setConfiguration(pr); }
 * pdfExporter.exportReport(); } catch (JRException e) { throw e; } catch
 * (IOException e) { throw e; } }
 * 
 *//**
	 * World导出方法
	 * <p>
	 * @param response
	 * <p>
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 模板文件的绝对路径
	 * <p>
	 * <p>
	 * @param fileName 输出文件名
	 * <p>
	 * <p>
	 * @param suffix 文件后缀名
	 * <p>
	 * <p>
	 * @param dr 输出配置信息
	 * <p>
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
/*
 * public static void worldExport(HttpServletResponse response, Map<String,
 * Object> params, String filePath, String fileName, ReportFileType suffix,
 * DocxReportConfiguration dr) throws JRException, IOException{ JasperPrint
 * jasperPrint; try { File jasperFile = new File(filePath); JasperReport
 * jasperReport = (JasperReport) JRLoader.loadObject(jasperFile); jasperPrint =
 * JasperFillManager.fillReport(jasperReport, params,
 * UtilPersistence.getConnection()); response.setCharacterEncoding("utf-8");
 * fileName = fileName + "." + suffix.toString();
 * response.setHeader("Content-Disposition","attachment;" + "filename=" + new
 * String(fileName.getBytes(), "ISO-8859-1"));
 * response.addHeader("Content-Type", "application/x-msword"); JRDocxExporter
 * worldExporter = new JRDocxExporter(); worldExporter.setExporterInput(new
 * SimpleExporterInput(jasperPrint)); worldExporter.setExporterOutput(new
 * SimpleOutputStreamExporterOutput(response.getOutputStream()));
 * if(!UvUtil.isNull(dr)){ worldExporter.setConfiguration(dr); }
 * worldExporter.exportReport(); } catch (JRException e) { throw e; } catch
 * (IOException e) { throw e; } }
 * 
 *//**
	 * Excle导出方法
	 * <p>
	 * @param response
	 * <p>
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 模板文件的绝对路径
	 * <p>
	 * <p>
	 * @param fileName 输出文件名
	 * <p>
	 * <p>
	 * @param suffix 文件后缀名
	 * <p>
	 * <p>
	 * @param xs 输出配置信息
	 * <p>
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
/*
 * public static void excleExport(HttpServletResponse response, Map<String,
 * Object> params, String filePath, String fileName, ReportFileType suffix,
 * XlsxReportConfiguration xs) throws JRException, IOException{ JasperPrint
 * jasperPrint; try { File jasperFile = new File(filePath); JasperReport
 * jasperReport = (JasperReport) JRLoader.loadObject(jasperFile); jasperPrint =
 * JasperFillManager.fillReport(jasperReport, params,
 * UtilPersistence.getConnection()); response.setCharacterEncoding("utf-8");
 * fileName = fileName + "." + suffix.toString();
 * response.setHeader("Content-Disposition","attachment;" + "filename=" + new
 * String(fileName.getBytes(), "ISO-8859-1"));
 * response.setContentType("application/vnd_ms-excel"); JRXlsxExporter
 * xlsxExporter = new JRXlsxExporter(); xlsxExporter.setExporterInput(new
 * SimpleExporterInput(jasperPrint)); xlsxExporter.setExporterOutput(new
 * SimpleOutputStreamExporterOutput(response.getOutputStream()));
 * if(!UvUtil.isNull(xs)){ xlsxExporter.setConfiguration(xs); }
 * xlsxExporter.exportReport(); } catch (JRException e) { throw e; } catch
 * (IOException e) { throw e; } }
 * 
 *//**
	 * 以HTML渲染报表
	 * <p>
	 * @param response
	 * <p>
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 文件的绝对路径
	 * <p>
	 * <p>
	 * @param exporterConfig 输出配置信息
	 * <p>
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
/*
 * public static void reportShow(HttpServletResponse response, Map<String,
 * Object> params, String filePath, SimpleHtmlExporterConfiguration
 * exporterConfig) throws JRException, IOException{ JasperPrint jasperPrint; try
 * { // 设定reponse的输出格式 response.setCharacterEncoding("utf-8"); File jasperFile =
 * new File(filePath); // 创建JasperReport对象 JasperReport jasperReport =
 * (JasperReport) JRLoader.loadObject(jasperFile); // 填充JasperReport jasperPrint
 * = JasperFillManager.fillReport(jasperReport, params,
 * UtilPersistence.getConnection()); HtmlExporter exporter = new HtmlExporter();
 * exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); //
 * 设定导出类型为response.getWriter() exporter.setExporterOutput(new
 * SimpleHtmlExporterOutput(response.getWriter()));
 * if(!UvUtil.isNull(exporterConfig)){
 * exporter.setConfiguration(exporterConfig); } exporter.exportReport(); } catch
 * (JRException e) { LOG.error("渲染报表文件: " + filePath + "出现异常!"); throw e; }
 * catch (IOException e) { LOG.error("渲染报表文件: " + filePath + "出现异常!"); throw e;
 * }
 * 
 * }
 * 
 *//**
	 * 以HTML渲染报表
	 * <p>
	 * @param response
	 * <p>
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 文件的绝对路径
	 * <p>
	 */
/*
 * public static void reportShow(HttpServletResponse response, Map<String,
 * Object> params, String filePath) throws JRException, IOException{
 * reportShow(response, params, filePath, null); }
 * 
 *//**
	 * 报表打印
	 * <p>
	 * @param response
	 * <p>
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 输入文件的绝对路径
	 * <p>
	 * <p>
	 * @param jr 输出配置信息
	 * <p>
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
/*
 * public static void reportPrint(HttpServletResponse response, Map<String,
 * Object> params, String filePath, JasperReportsContext jr) throws JRException{
 * JasperPrint jasperPrint; try { File jasperFile = new File(filePath);
 * JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
 * jasperPrint = JasperFillManager.fillReport(jasperReport, params,
 * UtilPersistence.getConnection()); response.setCharacterEncoding("utf-8");
 * JasperReportsContext jasperReportsContext = null; JasperPrintManager
 * jasperPrintManager = null; if(UvUtil.isNull(jr)){ jasperReportsContext = new
 * SimpleJasperReportsContext(); jasperPrintManager =
 * JasperPrintManager.getInstance(jasperReportsContext); }else {
 * jasperPrintManager = JasperPrintManager.getInstance(jr); }
 * jasperPrintManager.print(jasperPrint, true); } catch (JRException e) {
 * e.printStackTrace(); } }
 * 
 *//**
	 * 报表打印
	 * <p>
	 * @param response
	 * <p>
	 * <p>
	 * @param params 报表需要的参数
	 * <p>
	 * <p>
	 * @param filePath 输入文件的绝对路径
	 * <p>
	 * <p>
	 * @param jr 输出配置信息
	 * <p>
	 * 
	 * @throws JRException
	 * @throws IOException
	 *//*
		 * public static void reportPrint(HttpServletResponse response, Map<String,
		 * Object> params, String filePath) throws JRException{ try {
		 * reportPrint(response, params, filePath, null); } catch (JRException e) {
		 * e.printStackTrace(); } } }
		 */