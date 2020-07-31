package com.citictel.report.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ParamtersUtils {
	
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");// 注意月份是MM
	
	public static Map<String, Object> handleParameters(Map<String, Object> parameters) {
		parameters = parameters == null ? new HashMap<>() : parameters;
		Map<String, Object> map = new HashMap<>();
		parameters.forEach((key, value) -> {
			System.out.println(key + "->" + value);
			String val = (String) value;
			if (val.contains("${")) {
				if ("${M-1-S}".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getFirstDayOfMonth(-1).getTime());
					map.put(key, sqlDate);
				}
				if ("${M-1-E}".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getLastDayOfMonth(-1).getTime());
					map.put(key, sqlDate);
				}
				if ("${M-0-S}".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getFirstDayOfMonth(0).getTime());
					map.put(key, sqlDate);
				}
				if ("${M-0-E}".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getLastDayOfMonth(0).getTime());
					map.put(key, sqlDate);
				}
				if ("${D}".equals(val)) {
					Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
					map.put(key, sqlDate);
				}
				return;
			}
			if (val.contains("$")) {
				if ("$M-1-S".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getFirstDayOfMonth(-1).getTime());
					map.put(key, sqlDate);
				}
				if ("$M-1-E".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getLastDayOfMonth(-1).getTime());
					map.put(key, sqlDate);
				}
				if ("$M-0-S".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getFirstDayOfMonth(0).getTime());
					map.put(key, sqlDate);
				}
				if ("$M-0-E".equals(val)) {
					Date sqlDate = new java.sql.Date(DateUtils.getLastDayOfMonth(0).getTime());
					map.put(key, sqlDate);
				}
				if ("$D".equals(val)) {
					Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
					map.put(key, sqlDate);
				}
				return;
			}
			String pattern = "\\d{4}/\\d{1,2}/\\d{1,2}";
			boolean isMatch = Pattern.matches(pattern, val);
			System.out.println("isMatch===>{}"+isMatch);
			if (isMatch) {
				try {
					java.util.Date date = simpleDateFormat.parse(val);
					Date sqlDate = new java.sql.Date(date.getTime());
					map.put(key, sqlDate);
					return;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.put(key, value);
		});
		return map;
	}
}
