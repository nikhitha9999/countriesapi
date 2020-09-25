package com.countries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4068048520130713592L;

	public CountryNotFoundException() {
		super();
	}

	public CountryNotFoundException(String message) {
		super(message);
	}

	public CountryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
