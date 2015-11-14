package com.hqb.pplearn.common.util;

import java.util.Calendar;
import java.util.Locale;

public class JsonAdapter {

	private static boolean isForAndroid = false;

	public static String bean2json(Object object) {
		if (isForAndroid) {
			return GoogleJsonUtil.objectToJSON(object).toString();
		} else {
			return JsonUtil.bean2json(object);
		}
	}
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		System.out.println(cal.getTime().toString());
		
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    System.out.println("FirstDayOfWeek="+cal.getFirstDayOfWeek());
	    System.out.println(cal.getTime().toString());
	    
	    
	    cal = Calendar.getInstance(Locale.FRANCE);
	    System.out.println("FirstDayOfWeek="+cal.getFirstDayOfWeek());
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    System.out.println(cal.getTime().toString());
	}
}