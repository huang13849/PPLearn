package com.hqb.pplearn.common.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * JSON和JAVA的POJO的相互转换
 * 
 * JSONHelper.java
 */
public final class JsonUtil {

	private static JsonConfig jsonConfig = new JsonConfig();

	static {
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
	}

	public static String array2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		return jsonArray.toString();
	}

	public static <T> Object json2Array(String json, Class<T> valueClz) {
		JSONArray jsonArray = JSONArray.fromObject(json, jsonConfig);
		return JSONArray.toArray(jsonArray, valueClz);
	}

	// 将Collection 2 JSON
	public static String collection2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		return jsonArray.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Collection json2Collection(String json, Class collectionClz, Class valueClz) {
		JSONArray jsonArray = JSONArray.fromObject(json, jsonConfig);
		return JSONArray.toCollection(jsonArray, valueClz);
	}

	// 将Map转换成JSON
	public static String map2json(Object object) {
		JsonObject jsonObject = JsonObject.fromObject(object, jsonConfig);
		return jsonObject.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map json2Map(Object[] keyArray, String json, Class valueClz) {
		JsonObject jsonObject = JsonObject.fromObject(json, jsonConfig);
		Map classMap = new HashMap();

		for (int i = 0; i < keyArray.length; i++) {
			classMap.put(keyArray[i], valueClz);
		}

		return (Map) JsonObject.toBean(jsonObject, Map.class, classMap);
	}

	public static String bean2json(Object object) {
		JsonObject jsonObject = JsonObject.fromObject(object, jsonConfig);
		return jsonObject.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Object json2Object(String json, Class beanClz) {
		return JSONObject.toBean(JSONObject.fromObject(json, jsonConfig), beanClz);
	}

	public static String string2json(String key, String value) {
		JSONObject object = new JSONObject();
		object.put(key, value);
		return object.toString();
	}

	public static String json2String(String json, String key) {
		JSONObject jsonObject = JSONObject.fromObject(json, jsonConfig);
		return jsonObject.get(key).toString();
	}

	public static void main(String[] args) {
		/*
		 double d = 1234489.1234500000089;
		

		System.out.println(JsonObject.valueToString(d));
		*/
		
		BigDecimal minutesPerOrder = new BigDecimal(10);
		BigDecimal callDuration = new BigDecimal(12);   
		BigDecimal price = new BigDecimal(1);		
		BigDecimal actualAmount =  price.divide(minutesPerOrder).multiply(callDuration);
		System.out.println(actualAmount.doubleValue());
		// System.out.println(bean2json(new Person()));
	}
}