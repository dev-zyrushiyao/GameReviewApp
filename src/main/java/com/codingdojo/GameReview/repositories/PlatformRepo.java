package com.codingdojo.GameReview.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.codingdojo.GameReview.models.GamePlatformModel;

@Repository
public interface PlatformRepo extends PagingAndSortingRepository<GamePlatformModel, Long> {
	
	List<GamePlatformModel> findAll();
	List<GamePlatformModel> findByPlatformName(String platformName);
	
	//From PageSortRepo
	List<GamePlatformModel> findAll(Sort sort);
	Page<GamePlatformModel> findAll(Pageable pageable);
}
