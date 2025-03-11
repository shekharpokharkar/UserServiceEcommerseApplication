package com.javaExpress.ExceptionHandling;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GloalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		log.info("handleValidationExceptions :: MethodArgumentNotValidException.class");
		List<String> errors = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Error", errors);

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(HandlerMethodValidationException ex) {
		log.info("handleValidationExceptions :: HandlerMethodValidationException.class");

		List<String> errors = ex.getAllErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundExceptions(UserNotFoundException ex) {
		log.info("handleValidationExceptions :: handleUserNotFoundExceptions.class");

		List<String> msg = List.of(ex.getMessage());

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User Not Found", msg);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleExceptions(Exception ex) {
		log.info("handleValidationExceptions :: Exception.class");

		List<String> msg = List.of(ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed", msg);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

}
