package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;

@Controller
@RequestMapping("/billing-records/rate-baseds")
public class RateBasedBillingRecordController {

	@PostMapping("")
	public ModelAndView create(FlatFeeBillingRecord record, long clientId, Authentication auth) {


		return new ModelAndView("redirect:/billing-records");
	}

}
