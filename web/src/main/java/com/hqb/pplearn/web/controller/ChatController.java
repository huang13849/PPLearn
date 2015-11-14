package com.hqb.pplearn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {

	@RequestMapping(value = "/chatPage.jhtm", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String registerPage() {
		logger.info("request registerPage.jhtm");
		return "register";
	}
}
