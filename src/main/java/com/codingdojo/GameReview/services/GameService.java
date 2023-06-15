package com.codingdojo.GameReview.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.GameReview.auth.UserModel;
import com.codingdojo.GameReview.models.GameGenreModel;
import com.codingdojo.GameReview.models.GameModel;
import com.codingdojo.GameReview.models.GamePlatformModel;
import com.codingdojo.GameReview.models.GameReview;
import com.codingdojo.GameReview.repositories.GameRepo;



@Service
public class GameService {
	
	@Autowired
	private GameRepo gameRepo;
	
	public GameModel createGame(GameModel gameModel) {
		return this.gameRepo.save(gameModel);
	}
	
	public GameModel updateGame(GameModel gameModel) {
		return this.gameRepo.save(gameModel);
	}
	
	public void deleteGameId(Long id) {
		this.gameRepo.deleteById(id);
	}
	
	public void deleteAllGame() {
		this.gameRepo.deleteAll();
	}
	
	//searching 1 data
	public GameModel findGameId(Long id) {
		Optional<GameModel> gameOptional = this.gameRepo.findById(id);
		if(gameOptional.isPresent()) {
			return gameOptional.get();
		}else {
			return null;
		}
	}
	
	
	public List<GameModel> findAll(){
		return this.gameRepo.findAll();
	}
	
	public List<GameModel> findTitle(String title){
		return this.gameRepo.findByTitle(title);
	}
	
	//genre filter
	public List<GameModel> findGenreEntity(GameGenreModel genreEntity){
		return this.gameRepo.findByGenreEntity(genreEntity);
	}
	
	//platform filter
	public List<GameModel> findPlatformEntity(GamePlatformModel platformEntity){
		return this.gameRepo.findByPlatformEntity(platformEntity);
	}
	
	//both Genre and Platform filter
	public List<GameModel> filterGenrePlatform (GameGenreModel genreEntity, GamePlatformModel platformEntity){
		return this.gameRepo.findByGenreEntityAndPlatformEntity(genreEntity, platformEntity);
	}
	
	
	//add bookmark @ManyToMany List
	public void userAddBookmark(UserModel userModel , GameModel gameModel) {
			List<GameModel> bookmarkList = userModel.getGames();
			bookmarkList.add(gameModel);
			this.gameRepo.save(gameModel);
	}
	
	//remove bookmark [Update List] @ManyToMany List 
	public void userRemoveBookmark(UserModel userModel, GameModel gameModel) {
		List<GameModel> bookmarkList = userModel.getGames();
		bookmarkList.remove(gameModel);
		this.gameRepo.save(gameModel);
	}
	
	//filter reviews using gamemodel
	public List<GameModel> findByReviews(GameReview gameReviews){
		return this.gameRepo.findByReviewEntity(gameReviews);
	}
	
	

	
}
