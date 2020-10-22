package com.algaworks.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.osworks.domain.model.ServiceOrderStatus;

public class ServiceOrderModel {
	
	private ClientResumeModel client;
	
	private Long id;
	
	private String description;
	private BigDecimal price;
	private ServiceOrderStatus status;
	private OffsetDateTime openDate;
	private OffsetDateTime finishDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public OffsetDateTime getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(OffsetDateTime finishDate) {
		this.finishDate = finishDate;
	}
	public ClientResumeModel getClient() {
		return client;
	}
	public void setClient(ClientResumeModel client) {
		this.client = client;
	}
	
	
	
}
