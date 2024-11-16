package com.sam.blog.exceptions;

import com.sam.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		ApiResponse response = new ApiResponse(
				ex.getMessage(),
				false,
				ex.getResourceName(),
				ex.getFieldName(),
				ex.getFieldValue(),
				LocalDateTime.now(),
				"RESOURCE_NOT_FOUND"
		);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> response = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}


//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ApiResponse> globalException(Exception ex, WebRequest request) {
//
//		ApiResponse response = new ApiResponse(
//				"An unexpected error occurred",
//				false,
//				null,
//				null,
//				0L,
//				LocalDateTime.now(),
//				"INTERNAL_SERVER_ERROR"
//		);
//
//		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	
}
