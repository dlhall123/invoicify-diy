package com.lmig.gfc.invoicify.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	Company company;

	@ManyToOne
	User createdBy;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	List<InvoiceLineItem> invoiceLineItems;

	String invoiceNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<InvoiceLineItem> getInvoiceLineItems() {
		return invoiceLineItems;
	}

	public void setInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) {
		this.invoiceLineItems = invoiceLineItems;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

}
