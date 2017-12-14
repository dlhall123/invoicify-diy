package com.lmig.gfc.invoicify.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public abstract class BillingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private double amount;

	private String description;

	private double rate;

	private double quantity;

	@ManyToOne
	private User createdBy;

	@OneToOne(mappedBy = "billingRecord")
	private InvoiceLineItem lineItem;

	@ManyToOne
	private Company client;

	protected abstract double getTotal();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public InvoiceLineItem getLineItem() {
		return lineItem;
	}

	public void setLineItem(InvoiceLineItem lineItem) {
		this.lineItem = lineItem;
	}

	public Company getClient() {
		return client;
	}

	public void setClient(Company client) {
		this.client = client;
	}

}
