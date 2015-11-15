package com.hqb.pplearn.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hqb.pplearn.biz.model.User;
import com.hqb.pplearn.biz.service.PartnerMatchService;
import com.hqb.pplearn.web.helper.WebConstant;
import com.hqb.pplearn.web.helper.WebHelper;

@Controller
public class PartnerMatchController {
	
	@Autowired
	private PartnerMatchService partnerMatchService;
	
	@RequestMapping(value = WebConstant.PARTNERS_JHTM, method = RequestMethod.GET)
	public String searchAvailablePartners(HttpServletRequest request, Model model){
		User loginUser = WebHelper.getCurrentUserFromSession(request);
		if(loginUser == null){
			throw new RuntimeException();
		}
		
		List<User> availableUsers = partnerMatchService.searchAvailablePartners(loginUser);
		model.addAttribute("users", availableUsers);
		
		return WebConstant.PARTNERS_PAGE;
	}
}
