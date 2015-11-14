/**
 * Copyright(c) Runs Technology Co.,Ltd
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Runs 
 * Technology Co.,Ltd. ("Confidential Information"). You shall not disclose 
 * such Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with Runs.
 * For more information about Runs, welcome to http://www.runs.com
 * 
 * project: vanilla-utility
 * package: com.runs.vanilla.utility
 * file: JSONUtility.java
 * date: Mar 19, 2014
 * 
 * Revision History:
 * Date		Version		Name				Description
 * Mar 19, 2014	1.0			Franklin			Creation File
 */
package com.hqb.pplearn.common.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * description:
 * 
 * 
 * @author Franklin.Zhang --zhangyixuan
 * @date Mar 19, 2014 4:25:44 PM
 */
public class GoogleJsonUtil {
	public static JSONObject objectToJSON(Object obj){
		return new JSONObject(obj);
	}
	public static <T> JSONArray listToJSON(List<T> list){
		JSONArray array = new JSONArray();
		for(T t : list){
			JSONObject obj = new JSONObject(t);
			array.put(obj);
		}
		return array;
	}
}