package com.lmig.gfc.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {
	private CompanyRepository compRepo;
	private BillingRecordRepository billingRepo;

	public BillingRecordController(CompanyRepository c, BillingRecordRepository b) {
		compRepo = c;
		billingRepo = b;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");
		mv.addObject("companies", compRepo.findAll());
		mv.addObject("records", billingRepo.findAll());

		return mv;
	}

}
