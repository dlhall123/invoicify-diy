package com.lmig.gfc.invoicify.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.Invoice;
import com.lmig.gfc.invoicify.models.InvoiceLineItem;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.InvoiceRepository;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesApiController {
	private CompanyRepository compRepo;
	private BillingRecordRepository billRepo;
	private InvoiceRepository invRepo;

	public InvoicesApiController(CompanyRepository c, BillingRecordRepository br, InvoiceRepository i) {
		compRepo = c;
		billRepo = br;
		invRepo = i;
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

	@GetMapping("/clients/{clientId}")
	public List<BillingRecord> createInvoice(@PathVariable Long clientId) {
		Company client = compRepo.findOne(clientId);
		return billRepo.findByClientAndLineItemIsNull(client);
	}

	@PostMapping("/clients/{recordIds}")
	public Invoice createInvoice(@RequestBody Invoice invoice, @PathVariable long[] recordIds, Authentication auth) {
		System.out.println(invoice.getCompany().getId());

		User user = (User) auth.getPrincipal();
		Company client = compRepo.findOne(invoice.getCompany().getId());

		List<BillingRecord> billRecords = billRepo.findByIdIn(recordIds);

		ArrayList<InvoiceLineItem> invLineItems = new ArrayList<InvoiceLineItem>();

		for (int i = 0; i < billRecords.size(); i = i + 1) {
			InvoiceLineItem ili = new InvoiceLineItem();
			ili.setBillingRecord(billRecords.get(i));
			ili.setCreatedBy(user);
			ili.setInvoice(invoice);
			invLineItems.add(ili);
		}

		invoice.setInvoiceLineItems(invLineItems);
		invoice.setCreatedBy(user);
		invoice.setCompany(client);
		invRepo.save(invoice);
		return invoice;
	}

}
