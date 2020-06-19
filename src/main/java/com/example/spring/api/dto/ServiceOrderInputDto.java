package com.example.spring.api.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ServiceOrderInputDto {
	
	@NotBlank
	private String description;
	
	@NotNull
	private BigDecimal price;
	
	@Valid
	@NotNull
	private ClientInputDto client;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ClientInputDto getClient() {
		return client;
	}
	public void setClient(ClientInputDto client) {
		this.client = client;
	}
}
