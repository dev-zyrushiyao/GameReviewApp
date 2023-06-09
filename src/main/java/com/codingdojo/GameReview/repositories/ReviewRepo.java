package com.codingdojo.GameReview.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.GameReview.models.GameModel;
import com.codingdojo.GameReview.models.GameReview;

@Repository
public interface ReviewRepo extends CrudRepository<GameReview, Long> {
	
	List<GameReview> findAll();
	List<GameReview> findByRating(Integer rating);
	
	//Game comments
	List<GameReview> findByGameEntity(GameModel gameEntity);
	
	List<GameReview> findByRatingAndGameEntity(Integer rating , GameModel gameEntity);
	
}
