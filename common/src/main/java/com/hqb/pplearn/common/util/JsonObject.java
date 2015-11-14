package com.hqb.pplearn.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.springframework.util.ReflectionUtils;

public class JsonObject {
	private JSONObject jsonObject;

	public JsonObject(JSONObject fromObject) {
		this.jsonObject = fromObject;
	}

	public static JsonObject fromObject(Object object, JsonConfig jsonConfig) {
		return new JsonObject(JSONObject.fromObject(object, jsonConfig));
	}

	@SuppressWarnings("rawtypes")
	public String toString() {
		if (jsonObject.isNullObject()) {
			return JSONNull.getInstance().toString();
		}
		try {
			Iterator keys = jsonObject.keys();
			StringBuffer sb = new StringBuffer("{");
			Map properties = getProperties(jsonObject);
			while (keys.hasNext()) {
				if (sb.length() > 1) {
					sb.append(',');
				}
				Object o = keys.next();
				sb.append(JSONUtils.quote(o.toString()));
				sb.append(':');

				sb.append(valueToString(properties.get(o)));
			}
			sb.append('}');
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public String toString(int indentFactor, int indent) {
		if (jsonObject.isNullObject()) {
			return JSONNull.getInstance().toString();
		}
		int i;
		int n = jsonObject.size();
		if (n == 0) {
			return "{}";
		}
		if (indentFactor == 0) {
			return this.toString();
		}
		Iterator keys = jsonObject.keys();
		StringBuffer sb = new StringBuffer("{");
		int newindent = indent + indentFactor;
		Object o;
		if (n == 1) {
			o = keys.next();
			sb.append(JSONUtils.quote(o.toString()));
			sb.append(": ");
			Map properties = getProperties(jsonObject);
			sb.append(JSONUtils.valueToString(properties.get(o), indentFactor, indent));
		} else {
			while (keys.hasNext()) {
				o = keys.next();
				if (sb.length() > 1) {
					sb.append(",\n");
				} else {
					sb.append('\n');
				}
				for (i = 0; i < newindent; i += 1) {
					sb.append(' ');
				}
				sb.append(JSONUtils.quote(o.toString()));
				sb.append(": ");
				Map properties = getProperties(jsonObject);
				sb.append(JSONUtils.valueToString(properties.get(o), indentFactor, newindent));
			}
			if (sb.length() > 1) {
				sb.append('\n');
				for (i = 0; i < indent; i += 1) {
					sb.append(' ');
				}
			}
			for (i = 0; i < indent; i += 1) {
				sb.insert(0, ' ');
			}
		}
		sb.append('}');
		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	private Map getProperties(JSONObject jsonObject) {
		Field field = ReflectionUtils.findField(JSONObject.class, "properties");
		ReflectionUtils.makeAccessible(field);
		Object vo = ReflectionUtils.getField(field, jsonObject);
		if (vo instanceof Map) {
			return (Map) vo;
		} else {
			return new HashMap();
		}
	}

	public static String valueToString(Object value) {
		if (value == null || JSONUtils.isNull(value)) {
			return "null";
		}
		if (value instanceof JSONFunction) {
			return ((JSONFunction) value).toString();
		}
		if (value instanceof JSONString) {
			Object o;
			try {
				o = ((JSONString) value).toJSONString();
			} catch (Exception e) {
				throw new JSONException(e);
			}
			if (o instanceof String) {
				return (String) o;
			}
			throw new JSONException("Bad value from toJSONString: " + o);
		}
		if (value instanceof Double) {
			return String.format("%.2f", value);
		}
		if (value instanceof Number) {
			return JSONUtils.numberToString((Number) value);
		}
		if (value instanceof JSONObject) {
			return new JsonObject((JSONObject) value).toString();
		}
		if (value instanceof JSONArray) {
			return new JsonArray((JSONArray) value).toString();
		}
		if (value instanceof Boolean) {
			return value.toString();
		}
		return JSONUtils.quote(value.toString());
	}

	public static String valueToString(Object value, int indentFactor, int indent) {
		if (value == null || JSONUtils.isNull(value)) {
			return "null";
		}
		if (value instanceof JSONFunction) {
			return ((JSONFunction) value).toString();
		}
		if (value instanceof JSONString) {
			return ((JSONString) value).toJSONString();
		}
		if (value instanceof Double) {
			return String.format("%.2f", value);
		}
		if (value instanceof Number) {
			return JSONUtils.numberToString((Number) value);
		}
		if (value instanceof Boolean) {
			return value.toString();
		}
		if (value instanceof JSONObject) {
			return new JsonObject((JSONObject) value).toString(indentFactor, indent);
		}
		if (value instanceof JSONArray) {
			return new JsonArray((JSONArray) value).toString();
		}
		return JSONUtils.quote(value.toString());
	}

	@SuppressWarnings("rawtypes")
	public static Object toBean(JsonObject jsonObject, Class beanClass, Map classMap) {
		return JSONObject.toBean(jsonObject.getJsonObject(), beanClass, classMap);
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}
}