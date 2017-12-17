/*
 * package com.lmig.gfc.invoicify.controllers;
 * 
 * import org.springframework.security.core.Authentication; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.lmig.gfc.invoicify.models.Company; import
 * com.lmig.gfc.invoicify.models.RateBasedBillingRecord; import
 * com.lmig.gfc.invoicify.models.User; import
 * com.lmig.gfc.invoicify.services.BillingRecordRepository; import
 * com.lmig.gfc.invoicify.services.CompanyRepository; import
 * com.lmig.gfc.invoicify.services.UserRepository;
 * 
 * @Controller
 * 
 * @RequestMapping("/billing-records/rate-baseds") public class
 * RateBasedBillingRecordController { private CompanyRepository compRepo;
 * private BillingRecordRepository billRepo;
 * 
 * public RateBasedBillingRecordController(CompanyRepository c,
 * BillingRecordRepository b, UserRepository u) { compRepo = c; billRepo = b; }
 * 
 * @PostMapping("") public ModelAndView create(RateBasedBillingRecord record,
 * long clientId, Authentication auth) { Company client =
 * compRepo.findOne(clientId); record.setClient(client);
 * record.setCreatedBy((User) auth.getPrincipal()); billRepo.save(record);
 * return new ModelAndView("redirect:/billing-records"); }
 * 
 * }
 */