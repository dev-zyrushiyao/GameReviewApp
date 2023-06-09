package com.codingdojo.GameReview.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.GameReview.models.GameGenreModel;
import com.codingdojo.GameReview.models.GameModel;
import com.codingdojo.GameReview.models.GamePlatformModel;
import com.codingdojo.GameReview.models.GameReview;


@Repository
public interface GameRepo extends CrudRepository<GameModel, Long> {
	
	List<GameModel> findAll();
	List<GameModel> findByTitle(String title);
	
	//individual filter
	List<GameModel> findByGenreEntity(GameGenreModel genreEntity);
	List<GameModel> findByPlatformEntity(GamePlatformModel platformEntity);
	
	//filter platform and genre (List of Genre and Platform) into List of GameModel
	List<GameModel> findByGenreEntityAndPlatformEntity(GameGenreModel genreEntity, GamePlatformModel platformEntity);
	
	//filter review from a game
	List<GameModel> findByReviewEntity(GameReview reviewEntity);
 }
