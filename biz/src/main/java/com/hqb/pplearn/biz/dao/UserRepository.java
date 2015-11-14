package com.hqb.pplearn.biz.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hqb.pplearn.biz.model.User;

public interface UserRepository extends CrudRepository<User,String>{

	List<User> findByEmail(String email);

}