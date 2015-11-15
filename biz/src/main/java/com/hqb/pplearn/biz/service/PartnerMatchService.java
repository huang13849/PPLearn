package com.hqb.pplearn.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqb.pplearn.biz.dao.UserRepository;
import com.hqb.pplearn.biz.model.User;

@Service
public class PartnerMatchService {
	@Autowired
	private UserRepository userRepo;
	
	public List<User> searchAvailablePartners(User loginUser){
		if(loginUser == null){
			throw new IllegalArgumentException("User cannot be null here.");
		}
		
		List<User> matchableUsers = userRepo.findMatchableUsers(loginUser.getOffer(), loginUser.getWant());
		
		return matchableUsers;
	}
}
