package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjection;

public class SaleMinDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String sellerName;
	
	public SaleMinDTO(Long id, Double amount, LocalDate date, String sellerName) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerName = sellerName;
	}
	
	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
	}

	public SaleMinDTO(SaleMinProjection projection){
		id = projection.getId();
		date = projection.getDate();
		amount = projection.getAmount();
		sellerName = projection.getSellerName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	@Override
	public String toString() {
		return "SaleMinDTO [id=" + id + ", date=" + date + ", amount=" + amount + ", sellerName=" + sellerName + "]";
	}

}
