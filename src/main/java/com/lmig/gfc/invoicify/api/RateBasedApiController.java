package com.lmig.gfc.invoicify.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.RateBasedBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@RestController
@RequestMapping("/api/ratefees")
public class RateBasedApiController {
	private BillingRecordRepository billRepo;
	private CompanyRepository coRepo;

	public RateBasedApiController(BillingRecordRepository b, CompanyRepository c) {
		billRepo = b;
		coRepo = c;
	}

	@PostMapping("")
	public RateBasedBillingRecord create(@RequestBody RateBasedBillingRecord billRecord, Authentication auth) {
		User user = (User) auth.getPrincipal();
		billRecord.setCreatedBy(user);
		billRecord.setClient(coRepo.findOne(billRecord.getClient().getId()));
		System.out.println("Hello, David. I have a total of " + billRecord.getTotal());
		return billRepo.save(billRecord);
	}

}
