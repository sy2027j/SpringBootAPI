package com.example.TestProject.Exception;

import java.util.Date;
import java.util.Map;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	private Map<Object,String> map;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public void ErrorDetails2(Date timestamp, String message, String details, Map<Object,String> map) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.map=map;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}