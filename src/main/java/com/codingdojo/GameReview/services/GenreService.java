package com.codingdojo.GameReview.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.codingdojo.GameReview.models.GameGenreModel;

import com.codingdojo.GameReview.repositories.GenreRepo;


@Service
public class GenreService {
	
	@Autowired
	private GenreRepo genreRepo;

	
		// create
		public GameGenreModel createGenre(GameGenreModel gameGenreModel) {
			return this.genreRepo.save(gameGenreModel);
		}

		// update
		public GameGenreModel updateGenre(GameGenreModel gameGenreModel) {
			return this.genreRepo.save(gameGenreModel);
		}

		// find all
		public List<GameGenreModel> findAllGenre() {
			return this.genreRepo.findAll();
		}

		// find by id
		public GameGenreModel findGenreId(Long id) {
			Optional<GameGenreModel> optional = this.genreRepo.findById(id);

			if (optional.isPresent()) {
				return optional.get();
			} else {
				return null;
			}
		}

		// delete a category
		public void deleteGenreById(Long id) {
			this.genreRepo.deleteById(id);
		}

		// delete all category
		public void deleteAllGenre() {
			this.genreRepo.deleteAll();
		}
	
		public List<GameGenreModel> findGenre(String genre){
			return this.genreRepo.findByGenre(genre);
		}
		
		//PagingSort API 
		public Page<GameGenreModel> findAllPage(Pageable pageable){
			return this.genreRepo.findAll(pageable);
		}
}
