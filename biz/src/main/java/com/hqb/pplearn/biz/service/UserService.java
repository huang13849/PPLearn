package com.hqb.pplearn.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqb.pplearn.biz.dao.UserRepository;
import com.hqb.pplearn.biz.form.UserForm;
import com.hqb.pplearn.biz.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void createUser(final UserForm form) throws IllegalAccessException, InvocationTargetException {
		if (form != null) {

			if (existingUserEmail(form.getEmail())) {
				throw new RuntimeException("Email already registed.");
			}

			User user = new User();
			BeanUtils.copyProperties(user, form);
			userRepository.save(user);
		}
	}

	private boolean existingUserEmail(String email) {
		List<User> users = userRepository.findByEmail(email);
		return CollectionUtils.isNotEmpty(users);
	}
}