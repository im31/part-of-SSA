package com.im31.sta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotImplementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotImplementException() {
        super("Method not implemneted.");
    }

	public NotImplementException(String message) {
        super(message);
    }

    public NotImplementException(String message, Throwable cause) {
        super(message, cause);
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}