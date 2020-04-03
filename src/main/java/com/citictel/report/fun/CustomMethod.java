package com.citictel.report.fun;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.functions.annotations.Function;
import net.sf.jasperreports.functions.annotations.FunctionCategories;
import net.sf.jasperreports.functions.annotations.FunctionParameter;
import net.sf.jasperreports.functions.annotations.FunctionParameters;

@FunctionCategories({ com.citictel.report.fun.CustomFun.class })
public class CustomMethod {

	@Function("LAST_MONTH")
	public static String LAST_MONTH() {
		SimpleDateFormat _format = new SimpleDateFormat("yyyy-MMM",Locale.ENGLISH);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) -1 ); 
	    date = calendar.getTime();
	    String accDate = _format.format(date);
		return accDate;
	}

	@Function("GET_GAP_MONTH")
	@FunctionParameters({ @FunctionParameter("format"), @FunctionParameter("number"),
			@FunctionParameter("forceUpperCase") })
	public static String GET_GAP_MONTH(String format, Integer number) {
		SimpleDateFormat _format = new SimpleDateFormat(format,Locale.ENGLISH);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + number); 
	    date = calendar.getTime();
	    String accDate = _format.format(date);
		return accDate;
	}
	public static void main(String[] args) {
		System.out.println("22222222");
	}  

	
}