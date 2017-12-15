package com.lmig.gfc.invoicify.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesApiController {
	private CompanyRepository compRepo;
	private BillingRecordRepository billRepo;

	public InvoicesApiController(CompanyRepository c, BillingRecordRepository br) {
		compRepo = c;
		billRepo = br;
	}

	@GetMapping("/clients")
	public List<Company> chooseClient() {

		ArrayList<Company> clients = new ArrayList<Company>();
		for (Company client : compRepo.findAll()) {
			if (!billRepo.findByClientAndLineItemIsNull(client).isEmpty()) {
				clients.add(client);
			}
		}
		return clients;
	}

}
