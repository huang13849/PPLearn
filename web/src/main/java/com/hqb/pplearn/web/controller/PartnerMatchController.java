package com.hqb.pplearn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PartnerMatchController {

	@RequestMapping(value = "/partnerMatch2.jhtm", method = RequestMethod.GET)
	public String handleMatchInfo() {

		return "partnerMatch2";
	}
}
