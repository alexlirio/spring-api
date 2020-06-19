package com.example.spring.api.dto;

import javax.validation.constraints.NotBlank;

public class CommentInputDto {
	
	@NotBlank
	private String description;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
