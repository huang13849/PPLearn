package com.hqb.pplearn.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.util.JSONUtils;

public class JsonArray {
	private JSONArray jsonArray;

	public JsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	@SuppressWarnings("rawtypes")
	public List getElements() {
		if (this.jsonArray != null) {
			Field fd = ReflectionUtils.findField(JSONArray.class, "elements");
			fd.setAccessible(true);
			try {
				return (List) fd.get(jsonArray);
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList();
			}
		} else {
			return new ArrayList();
		}
	}

	/**
	 * Make a JSON text of this JSONArray. For compactness, no unnecessary
	 * whitespace is added. If it is not possible to produce a syntactically
	 * correct JSON text then null will be returned instead. This could occur if
	 * the array contains an invalid number.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * 
	 * @return a printable, displayable, transmittable representation of the
	 *         array.
	 */
	public String toString() {
		try {
			return '[' + join(",") + ']';
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Make a prettyprinted JSON text of this JSONArray. Warning: This method
	 * assumes that the data structure is acyclical.
	 * 
	 * @param indentFactor
	 *            The number of spaces to add to each level of indentation.
	 * @return a printable, displayable, transmittable representation of the
	 *         object, beginning with <code>[</code>&nbsp;<small>(left
	 *         bracket)</small> and ending with <code>]</code>
	 *         &nbsp;<small>(right bracket)</small>.
	 * @throws JSONException
	 */
	public String toString(int indentFactor) {
		if (indentFactor == 0) {
			return this.toString();
		}
		return toString(indentFactor, 0);
	}

	/**
	 * Make a prettyprinted JSON text of this JSONArray. Warning: This method
	 * assumes that the data structure is acyclical.
	 * 
	 * @param indentFactor
	 *            The number of spaces to add to each level of indentation.
	 * @param indent
	 *            The indention of the top level.
	 * @return a printable, displayable, transmittable representation of the
	 *         array.
	 * @throws JSONException
	 */
	public String toString(int indentFactor, int indent) {
		int len = this.jsonArray.size();
		if (len == 0) {
			return "[]";
		}
		if (indentFactor == 0) {
			return this.toString();
		}
		int i;
		StringBuffer sb = new StringBuffer("[");
		if (len == 1) {

			sb.append(JsonObject.valueToString(getElements().get(0), indentFactor, indent));
		} else {
			int newindent = indent + indentFactor;
			sb.append('\n');
			for (i = 0; i < len; i += 1) {
				if (i > 0) {
					sb.append(",\n");
				}
				for (int j = 0; j < newindent; j += 1) {
					sb.append(' ');
				}
				sb.append(JsonObject.valueToString(getElements().get(i), indentFactor, newindent));
			}
			sb.append('\n');
			for (i = 0; i < indent; i += 1) {
				sb.append(' ');
			}
			for (i = 0; i < indent; i += 1) {
				sb.insert(0, ' ');
			}
		}
		sb.append(']');
		return sb.toString();
	}

	public String join(String separator) {
		return join(separator, false);
	}

	/**
	 * Make a string from the contents of this JSONArray. The
	 * <code>separator</code> string is inserted between each element. Warning:
	 * This method assumes that the data structure is acyclical.
	 * 
	 * @param separator
	 *            A string that will be inserted between the elements.
	 * @return a string.
	 * @throws JSONException
	 *             If the array contains an invalid number.
	 */
	public String join(String separator, boolean stripQuotes) {
		int len = this.jsonArray.size();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i += 1) {
			if (i > 0) {
				sb.append(separator);
			}
			String value = JsonObject.valueToString(getElements().get(i));
			sb.append(stripQuotes ? JSONUtils.stripQuotes(value) : value);
		}
		return sb.toString();
	}
}
