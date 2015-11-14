package com.hqb.pplearn.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hqb.pplearn.biz.model.User;
import com.hqb.pplearn.biz.service.RongCloudService;
import com.hqb.pplearn.biz.service.UserService;
import com.hqb.pplearn.web.helper.WebHelper;

@Controller
public class ChatController {

	@Autowired
	private UserService userService;

	@Autowired
	private RongCloudService rongCloudService;

	private final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@RequestMapping(value = "/chatPage.jhtm", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String chatPage(String toUserMail, Model model, HttpServletRequest request) {
		logger.info("chatPage.jhtm");
		User loginUser = WebHelper.getCurrentUserFromSession(request);
		User toUser = userService.findUserByEmail(toUserMail);

		if (loginUser == null) {
			return "signIn";
		} else if (toUser == null) {
			throw new RuntimeException("Cannot find the user by email: " + toUserMail);
		} else {
		}
		String token = loginUser.getRongCloudToken();
		if (StringUtils.isBlank(token)) {
			token = rongCloudService.auth(loginUser.getId(), loginUser.getUserAccount(), loginUser.getHeadImageUrl());
			loginUser.setRongCloudToken(token);
			userService.update(loginUser);
		}
		model.addAttribute("token", token);
		model.addAttribute("toUser", toUser);
		return "chat";
	}
}
