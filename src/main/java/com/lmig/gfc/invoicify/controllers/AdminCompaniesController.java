package com.lmig.gfc.invoicify.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/admin/companies")
public class AdminCompaniesController {
	CompanyRepository repo;

	public AdminCompaniesController(CompanyRepository c) {
		repo = c;
	}

	@GetMapping("")
	public ModelAndView showDefault(@RequestParam(required = false) Boolean error) {
		ModelAndView mv = new ModelAndView("admin/companies/default");
		if (error != null && error) {
			mv.addObject("errorMessage", "You must type a name.");
		}
		mv.addObject("companies", repo.findAll());
		return mv;
	}

	@PostMapping("")
	public ModelAndView createCompany(@Valid Company company, BindingResult bindResult) {
		ModelAndView mv = new ModelAndView("redirect:/admin/companies");
		if (!bindResult.hasErrors()) {
			repo.save(company);
		} else {
			mv.setViewName("redirect:/admin/companies?error=true");
		}
		return mv;
	}

}
