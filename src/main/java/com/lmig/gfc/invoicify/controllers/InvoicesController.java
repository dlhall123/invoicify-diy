package com.lmig.gfc.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.services.InvoiceRepository;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {

	// private CompanyRepository compRepo;
	private InvoiceRepository invRepo;
	// private BillingRecordRepository billRepo;

	public InvoicesController(InvoiceRepository i) {
		// compRepo = c;
		invRepo = i;
		// billRepo = br;
	}

	@GetMapping("")
	public ModelAndView showInvoices() {
		ModelAndView mv = new ModelAndView("invoices/list");
		// mv.addObject("showTable", !invRepo.findAll().isEmpty());
		mv.addObject("invoices", invRepo.findAll());

		return mv;
	}

	/*
	 * @GetMapping("/clients") public ModelAndView chooseClient() { ModelAndView mv
	 * = new ModelAndView("invoices/clients");
	 * 
	 * ArrayList<Company> clients = new ArrayList<Company>(); for (Company client :
	 * compRepo.findAll()) { if
	 * (!billRepo.findByClientAndLineItemIsNull(client).isEmpty()) {
	 * clients.add(client); } } mv.addObject("clients", clients); return mv; }
	 * 
	 * @GetMapping("/clients/{clientId}") public ModelAndView
	 * createInvoice(@PathVariable Long clientId) { ModelAndView mv = new
	 * ModelAndView("invoices/billing-records-list");
	 * 
	 * Company client = compRepo.findOne(clientId);
	 * 
	 * mv.addObject("records", billRepo.findByClientAndLineItemIsNull(client));
	 * 
	 * return mv; }
	 * 
	 * @PostMapping("/clients/{clientId}") public String createInvoice(Invoice
	 * invoice, @PathVariable Long clientId, long[] recordIds, Authentication auth)
	 * {
	 * 
	 * User user = (User) auth.getPrincipal(); Company client =
	 * compRepo.findOne(clientId);
	 * 
	 * List<BillingRecord> billRecords = billRepo.findByIdIn(recordIds);
	 * 
	 * ArrayList<InvoiceLineItem> invLineItems = new ArrayList<InvoiceLineItem>();
	 * 
	 * for (int i = 0; i < billRecords.size(); i = i + 1) { InvoiceLineItem ili =
	 * new InvoiceLineItem(); ili.setBillingRecord(billRecords.get(i));
	 * ili.setCreatedBy(user); ili.setInvoice(invoice); invLineItems.add(ili); }
	 * 
	 * invoice.setInvoiceLineItems(invLineItems); invoice.setCreatedBy(user);
	 * invoice.setCompany(client); invRepo.save(invoice);
	 * 
	 * return "redirect:/invoices"; }
	 */

}
