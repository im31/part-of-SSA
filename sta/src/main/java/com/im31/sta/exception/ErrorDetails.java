package com.im31.sta.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorDetails {
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String error;
	private String message;
	private String details;
	private String errorInformationLink;

	public ErrorDetails(
			LocalDateTime timestamp,
			HttpStatus status,
			String error,
			String message,
			String details,
			String errorInformationLink) {
		
	    super();
	    
	    this.timestamp = timestamp;
	    this.status = status;
	    this.error = error;
	    this.message = message;
	    this.details = details;
	    this.errorInformationLink = errorInformationLink;
	  }

	public String getTime() {
		return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	@JsonIgnore
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getErrorInformationLink() {
		return errorInformationLink;
	}

	public void setErrorInformationLink(String errorInformationLink) {
		this.errorInformationLink = errorInformationLink;
	}
}
