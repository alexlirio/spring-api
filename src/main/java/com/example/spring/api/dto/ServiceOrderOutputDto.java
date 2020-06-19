package com.example.spring.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.example.spring.domain.model.ServiceOrderStatus;

public class ServiceOrderOutputDto {
	
	private Long id;
//	private String nameClient; //"nameClient" is the "ModelMapper way" to map "Client.name property" with "nameClient property"
	private ClientOutputDto client; //"client" is the "ModelMapper way" to map "Client class" to "ClientOutputDto class"
	private String description;
	private BigDecimal price;
	private ServiceOrderStatus status;
	private OffsetDateTime openDate;
	private OffsetDateTime closeDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClientOutputDto getClient() {
		return client;
	}
	public void setClient(ClientOutputDto client) {
		this.client = client;
	}
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
	public ServiceOrderStatus getStatus() {
		return status;
	}
	public void setStatus(ServiceOrderStatus status) {
		this.status = status;
	}
	public OffsetDateTime getOpenDate() {
		return openDate;
	}
	public void setOpenDate(OffsetDateTime openDate) {
		this.openDate = openDate;
	}
	public OffsetDateTime getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(OffsetDateTime closeDate) {
		this.closeDate = closeDate;
	}
}
