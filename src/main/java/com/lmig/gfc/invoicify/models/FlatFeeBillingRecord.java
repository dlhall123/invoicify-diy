package com.lmig.gfc.invoicify.models;

import javax.persistence.Entity;

@Entity
public class FlatFeeBillingRecord extends BillingRecord {

	public double amount;

	@Override
	public double getTotal() {
		return amount;
	}

}
