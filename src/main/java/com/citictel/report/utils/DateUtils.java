package com.citictel.report.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date getFirstDayOfMonth(int diff) {
		Calendar calendar = Calendar.getInstance();
		int _month = calendar.get(Calendar.MONTH);

		// 获取某月最小天数
		int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置月份
		calendar.set(Calendar.MONTH, _month + diff);
		calendar.set(Calendar.DAY_OF_MONTH, firstDay);
		calendar.set(Calendar.HOUR, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getLastDayOfMonth(int diff) {
		Calendar calendar = Calendar.getInstance();
		int _month = calendar.get(Calendar.MONTH);
		// 设置月份
		int month = _month + diff;
		int year = calendar.get(Calendar.YEAR);
		int lastDay = getLastDay(year, month + 1);
		System.out.println("year:" + year + "   month:" + (month + 1) + "--->" + lastDay);

		calendar.set(Calendar.MONTH, month);
		// 设置日历中月份的最大天数
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		// 设置每天的最大小时
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		// 设置每小时最大分钟
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		// 设置每分钟最大秒
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		return calendar.getTime();
	}

	public static int getLastDay(int year, int month) {

		int end_data_mid = 31;
		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				end_data_mid = 29;
			} else {
				end_data_mid = 28;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			end_data_mid = 30;
		}
		return end_data_mid;
	}

	public static void main(String[] args) {
		System.out.println(getFirstDayOfMonth(-6));
		System.out.println(getFirstDayOfMonth(-5));
		System.out.println(getFirstDayOfMonth(-4));
		System.out.println(getFirstDayOfMonth(-3));
		System.out.println(getFirstDayOfMonth(-2));
		System.out.println(getFirstDayOfMonth(-1));
		System.out.println(getFirstDayOfMonth(0));
		System.out.println(getFirstDayOfMonth(1));
		System.out.println(getFirstDayOfMonth(2));
		System.out.println(getLastDay(2020, 6));
		System.out.println(getLastDay(2020, 7));

		System.out.println(getLastDayOfMonth(-6));
		System.out.println(getLastDayOfMonth(-5));
		System.out.println(getLastDayOfMonth(-4));
		System.out.println(getLastDayOfMonth(-3));
		System.out.println(getLastDayOfMonth(-2));
		System.out.println(getLastDayOfMonth(-1));
		System.out.println(getLastDayOfMonth(0));
		System.out.println(getLastDayOfMonth(1));
		System.out.println(getLastDayOfMonth(2));
	}
}
