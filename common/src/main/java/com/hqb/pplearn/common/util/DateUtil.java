package com.hqb.pplearn.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String YEAR_MONTH_FORMAT = "yyyy-MM";
	public static final String YEARMONTHDAY_FORMAT = "yyyyMMdd";

	public static String getCurDateTime() {
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String curTime = formatter.format(new Date());
		return curTime;
	}

	public static String getStandardDateTime() {
		DateFormat formatter = new SimpleDateFormat(LONG_DATE_FORMAT);
		String curTime = formatter.format(new Date());
		return curTime;
	}

	public static Date stringToLongDate(String value) {
		return stringToDate(value, LONG_DATE_FORMAT);
	}

	public static Date stringToDate(String value, String format) {
		if (value == null) {
			return null;
		}
		DateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formatter.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date stringToShortDate(String value) {
		return stringToDate(value, SHORT_DATE_FORMAT);
	}

	public static String dateToLongString(Date value) {
		return dateToFormatString(value, LONG_DATE_FORMAT);
	}

	public static String dateToShortString(Date value) {
		return dateToFormatString(value, SHORT_DATE_FORMAT);
	}

	public static String dateToFormatString(Date value, String format) {
		if (value == null)
			return "";
		DateFormat formatter = new SimpleDateFormat(format);
		String shortDate = formatter.format(value);
		return shortDate;
	}

	public static String dataToLocaleLongString(Date value) {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG);
		String longDate = formatter.format(value);
		return longDate;
	}

	public static String dataToLocaleShortString(Date value) {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT);
		String longDate = formatter.format(value);
		return longDate;
	}

	public static String formatToYearAndMonth(Date value) {
		DateFormat formatter = new SimpleDateFormat(YEAR_MONTH_FORMAT);
		String date = formatter.format(value);
		return date;
	}

	public static Date firstDayOfYearMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		resetTimeToZero(calendar);
		return calendar.getTime();
	}

	public static Date firstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		resetTimeToZero(calendar);
		return calendar.getTime();
	}

	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return 7;
		} else {
			return dayOfWeek - 1;
		}
	}

	public static Date mondayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		resetTimeToZero(calendar);
		return calendar.getTime();
	}

	public static Date sundayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		calendar.add(Calendar.DATE, -day_of_week + 7);
		return calendar.getTime();
	}

	private static void resetTimeToZero(Calendar calendar) {
		Date dt = calendar.getTime();
		calendar.setTime(stringToDate(dateToFormatString(dt, YEARMONTHDAY_FORMAT), YEARMONTHDAY_FORMAT));
	}

	public static Date lastDayOfYearMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int value = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, value);
		// calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	public static Date nextYearAndMonth(String value) {
		Calendar calendar = Calendar.getInstance();

		DateFormat formatter = new SimpleDateFormat(YEAR_MONTH_FORMAT);
		Date date = null;
		try {
			date = formatter.parse(value);
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getTime();
	}

	public static Date nextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date[] calculateWeekSect(Date begin, Date end) {
		Calendar calendar = Calendar.getInstance();
		Date[] weekDate = new Date[2];
		calendar.setTime(begin);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		if (calendar.getTime().after(begin))
			calendar.add(Calendar.DATE, -7);
		weekDate[0] = calendar.getTime();

		calendar.setTime(end);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		if (calendar.getTime().before(end))
			calendar.add(Calendar.DATE, 7);
		weekDate[1] = calendar.getTime();

		return weekDate;
	}

	public static int getYear(Date date) {
		return getDatePartValue(date, Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		return getDatePartValue(date, Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		return getDatePartValue(date, Calendar.DATE);
	}

	private static int getDatePartValue(Date date, int field) {
		if (date == null) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}

	/**
	 * @return
	 */
	public static int getCurrentTime() {
		return new Long(System.currentTimeMillis() / 1000).intValue();
	}

	/**
	 * @return return the start of today, which means the hour, minute and
	 *         second are all set to 0.
	 */
	public static Date getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * @param date
	 *            Date to be added such as 2008-06-17 00:00:00
	 * @param days
	 *            how days to be added
	 * @return the date added the specified days.
	 */
	public static Date incrementDate(Date date, int days) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days == 0 ? 1 : days);
		return calendar.getTime();
	}

	/**
	 * @param date
	 *            Date to be added such as 2008-06-17 00:00:00
	 * @param days
	 *            how minutes to be added
	 * @return the date added the specified minutes.
	 */
	public static Date incrementDateByMinute(Date date, int minute) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute == 0 ? 1 : minute);
		return calendar.getTime();
	}

	/**
	 * @param date
	 *            Date to be added such as 2008-06-17 00:00:00
	 * @param days
	 *            how seconds to be added
	 * @return the date added the specified seconds.
	 */
	public static Date incrementDateBySecond(Date date, int second) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second == 0 ? 1 : second);
		return calendar.getTime();
	}

	/**
	 * @param date
	 *            Date to be added such as 2008-06-17 00:00:00
	 * @param days
	 *            how seconds to be added
	 * @return the date added the specified seconds.
	 */
	public static Date incrementDateByMonth(Date date, int monthOffset) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (monthOffset != 0) {
			calendar.add(Calendar.MONTH, monthOffset);
		}
		return calendar.getTime();
	}

	public static int daysDifferent(Date startDate, Date endDate) {
		return (int) Math.abs((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
	}

	public static int minutesDifferent(Date startDate, Date endDate) {
		return (int) Math.abs((endDate.getTime() - startDate.getTime()) / (60 * 1000)) + 1;
	}

	public static void main(String[] args) {
		/*
		 * Date now = DateUtil.stringToDate("20140202", "yyyyMMdd");
		 * 
		 * System.out.println(now); System.out.println(getMonth(now));
		 * System.out.println(getMonth(now));
		 * 
		 * Date lastDay = DateUtil.incrementDateByMonth(now, -1);
		 * System.out.println(lastDay); System.out.println(getYear(lastDay));
		 * System.out.println(getMonth(lastDay));
		 * 
		 * lastDay = DateUtil.incrementDateByMonth(new Date(), -2);
		 * System.out.println(lastDay); System.out.println(getYear(lastDay));
		 * System.out.println(getMonth(lastDay));
		 * 
		 * lastDay =
		 * DateUtil.incrementDateByMonth(DateUtil.stringToDate("20140101",
		 * "yyyyMMdd"), -2); System.out.println(lastDay);
		 * System.out.println(getYear(lastDay));
		 * System.out.println(getMonth(lastDay));
		 */

		/*
		 * Date dt1 = DateUtil.stringToDate("20140202", "yyyyMMdd"); Date dt2 =
		 * DateUtil.stringToDate("20140208", "yyyyMMdd");
		 * System.out.println(daysDifferent(dt1, dt2));
		 */

		System.out.println(DateUtil.getDayOfWeek(DateUtil.incrementDate(new Date(), 1)));
	}
}
