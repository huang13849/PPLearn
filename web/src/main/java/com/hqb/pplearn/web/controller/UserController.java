package com.hqb.pplearn.web.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hqb.pplearn.biz.form.UserForm;
import com.hqb.pplearn.biz.model.User;
import com.hqb.pplearn.biz.service.UserService;
import com.hqb.pplearn.biz.validate.UserValidator;
import com.hqb.pplearn.web.helper.WebConstant;
import com.hqb.pplearn.web.helper.WebHelper;

@Controller
// @RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Value("${imageAppRootPath}")
	private String imageAppRootPath;

	private String headImagesPath = "upload" + "/" + "user";

	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(new UserValidator());
	}

	@RequestMapping(value = WebConstant.REGISTER_PAGE_JHTM, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String registerPage() {
		logger.info("request registerPage.jhtm");
		return WebConstant.REGISTER_PAGE;
	}

	@RequestMapping(value = WebConstant.REGISTER_JHTM, method = RequestMethod.POST)
	public String register(@Validated UserForm form, Model model, HttpServletRequest request, BindingResult result) throws IllegalAccessException, InvocationTargetException {
		logger.info("request register.jhtm");
		if (result.hasErrors()) {
			String errMsg = WebHelper.buildErrorMsg(result);
			throw new RuntimeException(errMsg);
		} else {
			userService.createUser(form);
			return WebConstant.REDIRECT_INDEX_HTM;
		}
	}
	
	@RequestMapping(value = WebConstant.LOGIN_PAGE_JHTM, method = RequestMethod.GET)
	public String loginPage() {
		logger.info("request registerPage.jhtm");
		return WebConstant.SIGN_IN_PAGE;
	}
	
	@RequestMapping(value = WebConstant.LOGIN_JHTM, method =RequestMethod.POST)
	public String login(HttpServletRequest request, String userId, String password) {
		logger.info("validating user loggin......");
		String nextPage = "";
		User loginUser = userService.findUserByEmail(userId);
		if(loginUser != null && password.equals(loginUser.getPassword())) {
			logger.info("User[" + loginUser.getEmail() + "] logged in.");
			WebHelper.saveCurrentUserToSeesion(request, loginUser);
			nextPage = "redirect:" + WebConstant.PARTNERS_JHTM;
		} else {
			logger.info("Invaid user id[" + loginUser.getEmail() + "]");
			nextPage = WebConstant.SIGN_IN_PAGE;
		}
		return nextPage;
	}
}
