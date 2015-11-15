package com.hqb.pplearn.biz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hqb.pplearn.biz.model.User;

public interface UserRepository extends CrudRepository<User,String>{

	List<User> findByEmail(String email);

	@Query(value="SELECT u FROM User u WHERE u.offer = :want AND u.want = :offer")
	List<User> findMatchableUsers(@Param("offer") String offer, @Param("want")  String want);
}