package com.codingdojo.GameReview.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.GameReview.models.GameGenreModel;


@Repository
public interface GenreRepo extends CrudRepository<GameGenreModel, Long> {
	
	List<GameGenreModel> findAll();
	List<GameGenreModel> findByGenre(String genre);
	
	
	

}
