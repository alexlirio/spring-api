package com.example.spring.api.dto;

import javax.validation.constraints.NotNull;

public class ClientInputDto {
	
	@NotNull
	private Long id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
