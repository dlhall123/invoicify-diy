package com.lmig.gfc.invoicify.models;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class RateBasedBillingRecord extends BillingRecord {

	@Transient
	private double total;

	@Override
	protected double getTotal() {
		total = super.getRate() * super.getQuantity();
		return total;
	}

}
