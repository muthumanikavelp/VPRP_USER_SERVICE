package com.vprp.user.model;

public class CommonResponse {
	private long timeStamp;
	private int statusCode;
	private String statusMessage;
	private Integer count;
	public long getTimeStamp() {
		return java.lang.System.currentTimeMillis();
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return this.count;
	}

}
