package com.lmig.gfc.invoicify.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;

@Repository
public interface BillingRecordRepository extends JpaRepository<BillingRecord, Long> {
	public List<BillingRecord> findByClientAndLineItemIsNull(Company client);

}
