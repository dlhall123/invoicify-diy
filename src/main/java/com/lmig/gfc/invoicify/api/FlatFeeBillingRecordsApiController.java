package com.lmig.gfc.invoicify.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@RestController
@RequestMapping("/api/flatfees")
public class FlatFeeBillingRecordsApiController {
	private BillingRecordRepository billRepo;
	private CompanyRepository coRepo;

	FlatFeeBillingRecordsApiController(BillingRecordRepository b, CompanyRepository c) {
		billRepo = b;
		coRepo = c;
	}

	@PostMapping("")
	public FlatFeeBillingRecord create(@RequestBody FlatFeeBillingRecord billRecord, Authentication auth) {
		User user = (User) auth.getPrincipal();
		billRecord.setCreatedBy(user);
		billRecord.setClient(coRepo.findOne(billRecord.getClient().getId()));

		return billRepo.save(billRecord);

	}

}
