package com.hqb.pplearn.common.util;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements  JsonValueProcessor {

	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return process(arg0);
	}

	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		return process(arg1);
	}
	
    private Object process(Object value){  
        if(value instanceof Date){    
            return DateUtil.dateToFormatString((Date)value, DateUtil.YEARMONTHDAY_FORMAT);
        }    
        return value == null ? "" : value.toString();    
    } 

}
