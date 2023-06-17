package com.codingdojo.GameReview.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.GameReview.models.GameGenreModel;


@Repository
public interface GenreRepo extends PagingAndSortingRepository<GameGenreModel, Long> {
	
	//From Crud (PageSortRepo extends Crud)
	List<GameGenreModel> findAll(); 
	List<GameGenreModel> findByGenre(String genre);
	
	//From PageSortRepo
	List<GameGenreModel> findAll(Sort sort);
	Page<GameGenreModel> findAll(Pageable pageable);
	
	
}
