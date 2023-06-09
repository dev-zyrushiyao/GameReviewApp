package com.codingdojo.GameReview.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.GameReview.auth.UserRoleModel;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRoleModel, Long> {
	List<UserRoleModel> findAll();
	
	List<UserRoleModel> findByName(String name);
}
