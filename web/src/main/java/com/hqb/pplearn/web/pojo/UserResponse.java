package com.hqb.pplearn.web.pojo;

public class UserResponse {
	private boolean requestHandleStatus = false;
	private String userId;
	private String message;	
	private Object result;
	
	public boolean isRequestHandleStatus() {
		return requestHandleStatus;
	}
	public void setRequestHandleStatus(boolean requestHandleStatus) {
		this.requestHandleStatus = requestHandleStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}	
}