package com.sam.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {

	private String resourceName;
	private String fieldName;
	private Object fieldValue;

	public ApiException(String message, String resourceName, String fieldName, Object fieldValue) {
		super(message); // Passing the message to the RuntimeException constructor
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
