package com.sam.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private String message;
	private boolean success;
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	private LocalDateTime timestamp;
	private String errorCode;
}
