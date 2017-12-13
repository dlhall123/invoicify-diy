package com.lmig.gfc.invoicify.controllers;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.Invoice;
import com.lmig.gfc.invoicify.models.InvoiceLineItem;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.InvoiceRepository;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {

	private CompanyRepository compRepo;
	private InvoiceRepository invRepo;
	private BillingRecordRepository billRepo;

	public InvoicesController(CompanyRepository c, InvoiceRepository i, BillingRecordRepository br) {
		compRepo = c;
		invRepo = i;
		billRepo = br;
	}

	@GetMapping("")
	public ModelAndView showInvoices() {
		ModelAndView mv = new ModelAndView("invoices/list");
		mv.addObject("showTable", !invRepo.findAll().isEmpty());
		mv.addObject("invoices", invRepo.findAll());

		return mv;
	}

	@GetMapping("/clients")
	public ModelAndView chooseClient() {
		ModelAndView mv = new ModelAndView("invoices/clients");
		ArrayList<Company> clients = new ArrayList<Company>();
		for (Company client : compRepo.findAll()) {
			for (BillingRecord bill : billRepo.findAll()) {
				if (bill.getClient().equals(client) && bill.getLineItem() == null) {
					clients.add(client);
				}

			}
		}
		mv.addObject("clients", clients);
		// mv.addObject("clients", compRepo.findAll());

		return mv;
	}

	@GetMapping("/clients/{clientId}")
	public ModelAndView createInvoice(@PathVariable Long clientId) {
		ModelAndView mv = new ModelAndView("invoices/billing-records-list");

		Company client = compRepo.findOne(clientId);

		mv.addObject("records", billRepo.findByClientAndLineItemIsNull(client));

		return mv;
	}

	@PostMapping("/clients/{clientId}")
	public String createInvoice(Invoice invoice, @PathVariable Long clientId, long[] recordIds, Authentication auth) {

		User user = (User) auth.getPrincipal();
		Company client = compRepo.findOne(clientId);

		ArrayList<BillingRecord> billRecords = new ArrayList<BillingRecord>();

		for (Long id : recordIds) {
			billRecords.add(billRepo.findOne(id));
		}

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

		return "redirect:/invoices";
	}

}
