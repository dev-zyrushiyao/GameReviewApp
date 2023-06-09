package com.codingdojo.GameReview.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.codingdojo.GameReview.models.GamePlatformModel;

import com.codingdojo.GameReview.repositories.PlatformRepo;

@Service
public class PlatformService {

	@Autowired
	private PlatformRepo platformRepo;

	// create
	public GamePlatformModel createPlatform(GamePlatformModel gamePlatformModel) {
		return this.platformRepo.save(gamePlatformModel);
	}

	// update
	public GamePlatformModel updatePlatform(GamePlatformModel gamePlatformModel) {
		return this.platformRepo.save(gamePlatformModel);
	}

	// find all
	public List<GamePlatformModel> findAllPlatform() {
		return this.platformRepo.findAll();
	}

	// find by id
	public GamePlatformModel findPlaformId(Long id) {
		Optional<GamePlatformModel> optional = this.platformRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	// delete a category
	public void deletePlatform(Long id) {
		this.platformRepo.deleteById(id);
	}

	// delete all category
	public void deleteAllPlatform() {
		this.platformRepo.deleteAll();
	}

}
