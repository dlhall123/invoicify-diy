package com.lmig.gfc.invoicify.models;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class FlatFeeBillingRecord extends BillingRecord {
	@Transient
	private double total;

	@Override
	protected double getTotal() {
		total = super.getAmount();
		return total;
	}

}
