package com.javaExpress.ExceptionHandling;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

	private int status;
	private String message;
	private LocalDateTime timestamp;
	private List<String> errors;

	public ErrorResponse(int status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.timestamp = LocalDateTime.now();
		this.errors = errors;
	}
}
