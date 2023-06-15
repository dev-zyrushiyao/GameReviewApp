package com.codingdojo.GameReview.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.GameReview.models.GamePlatformModel;

@Repository
public interface PlatformRepo extends CrudRepository<GamePlatformModel, Long> {
	
	List<GamePlatformModel> findAll();
	List<GamePlatformModel> findByPlatformName(String platformName);
	
}
