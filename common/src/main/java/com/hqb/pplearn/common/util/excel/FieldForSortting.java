package com.hqb.pplearn.common.util.excel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * The <code>FieldForSortting</code>
 * 
 * @author sageeras.wang
 * @version 1.0, Created at 2013年9月17日
 */
public class FieldForSortting {
	private Member field;
	private int index;

	/**
	 * @param field
	 */
	public FieldForSortting(Member field) {
		super();
		this.field = field;
	}

	/**
	 * @param field
	 * @param index
	 */
	public FieldForSortting(Member field, int index) {
		super();
		this.field = field;
		this.index = index;
	}

	/**
	 * @return the field
	 */
	public Member getField() {
		return field;
	}

	public Field getFieldOnly() {
		if (field instanceof Field) {
			return (Field) field;
		
		} else {
			return null;
		}
	}
	
	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	public Object getValue(Object dataObj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (field instanceof Field) {
			Field fd = (Field) field;
			fd.setAccessible(true);
			return fd.get(dataObj);
		} else if (field instanceof Method) {
			Method mth = (Method) field;
			return mth.invoke(dataObj);
		} else {
			return null;
		}
	}

	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		if (field instanceof Field) {
			Field fd = (Field) field;
			return fd.getAnnotation(annotationClass);
		} else if (field instanceof Method) {
			Method mth = (Method) field;
			return mth.getAnnotation(annotationClass);
		} else {
			return null;
		}
	}

}
