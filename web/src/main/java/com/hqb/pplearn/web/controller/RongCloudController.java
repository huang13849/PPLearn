package com.hqb.pplearn.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqb.pplearn.biz.service.RongCloudService;
import com.hqb.pplearn.common.util.JsonUtil;
import com.hqb.pplearn.web.pojo.UserResponse;

@Controller
public class RongCloudController {
	private  final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private RongCloudService rongCloudService;

	@RequestMapping(value = "getUserRongCloudToken.jhtm", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String login(@RequestParam String userEmail) {
		logger.info("userEmail = " + userEmail);
		
		UserResponse response = new UserResponse();
		try {
			String token = rongCloudService.getRongCloudToken(userEmail);
			response.setResult(token);
			response.setRequestHandleStatus(true);
		} catch (Exception ex) {
			response.setRequestHandleStatus(false);
			response.setMessage(ex.getMessage());
		}
		String resultJson = JsonUtil.bean2json(response); 
		logger.debug(resultJson);
		return resultJson;
	}
}
