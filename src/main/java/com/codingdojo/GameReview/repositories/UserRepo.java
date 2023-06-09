package com.codingdojo.GameReview.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.codingdojo.GameReview.auth.UserModel;

@Repository
public interface UserRepo extends CrudRepository<UserModel, Long> {
	
	UserModel findByUserName(String userName);
	
	
}
