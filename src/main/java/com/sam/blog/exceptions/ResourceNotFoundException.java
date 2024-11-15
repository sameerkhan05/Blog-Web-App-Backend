package com.sam.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	private LocalDateTime timestamp;
	private String errorCode;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.timestamp = LocalDateTime.now();
		this.errorCode = "RESOURCE_NOT_FOUND";
	}
}
