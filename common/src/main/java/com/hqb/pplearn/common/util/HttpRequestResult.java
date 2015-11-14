package com.hqb.pplearn.common.util;

import org.apache.http.HttpEntity;

public class HttpRequestResult {
	private HttpEntity responseEntity;
	private boolean isSuccessed;
	private String responseContent;

	public HttpRequestResult() {
	}
	public HttpRequestResult(HttpEntity responseEntity, boolean isSuccessed, String responseContent) {
		super();
		this.responseEntity = responseEntity;
		this.isSuccessed = isSuccessed;
		this.responseContent = responseContent;
	}

	public HttpEntity getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(HttpEntity responseEntity) {
		this.responseEntity = responseEntity;
	}

	public boolean isSuccessed() {
		return isSuccessed;
	}

	public void setSuccessed(boolean isSuccessed) {
		this.isSuccessed = isSuccessed;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
}
