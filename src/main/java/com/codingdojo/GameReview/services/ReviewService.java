package com.codingdojo.GameReview.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.GameReview.models.GameModel;
import com.codingdojo.GameReview.models.GameReview;
import com.codingdojo.GameReview.repositories.ReviewRepo;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepo reviewRepo;
	
	//create comment
	public GameReview createReview(GameReview gameReview) {
		return reviewRepo.save(gameReview);
	}
	
	//update comment
	public GameReview updateReview(GameReview gameReview) {
		return reviewRepo.save(gameReview);
	}
	
	//find all Ratings of GameReview table
	public List<GameReview> findRating(Integer rating) {
		return this.reviewRepo.findByRating(rating);
	}
	
	//find specific comments per rating of comments
	public List<GameReview> FindCommentsRatingOfGame(Integer rating , GameModel gameModel){
		return this.reviewRepo.findByRatingAndGameEntity(rating, gameModel);
	}
	
	//get all comments
	public List<GameReview> viewAllComment(){
		return reviewRepo.findAll();
	}
	
	//get all comments per game
	public List<GameReview> findGameEntityComment(GameModel gameModel){
		return reviewRepo.findByGameEntity(gameModel);
	}
	
	public GameReview findReviewId(Long id) {
		return this.reviewRepo.findById(id).orElseGet(null);
	}
	
	public void deleteReviewId(Long id) {
		this.reviewRepo.deleteById(id);
	}
}
