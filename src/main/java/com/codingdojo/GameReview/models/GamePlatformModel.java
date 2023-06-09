package com.codingdojo.GameReview.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "game_platform")
public class GamePlatformModel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Field should not be blank")
	@Size(min=2 , max=20 , message = "Platform should have 2 ~ 20 characters")
	private String platformName;
	
	@Column(updatable = false)
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy = "platformEntity" , fetch = FetchType.LAZY)
	private List<GameModel> gameEntity;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	//super constructor
	public GamePlatformModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//field constructor
	public GamePlatformModel(@NotBlank(message = "Field should not be blank") String platformName) {
		super();
		this.platformName = platformName;
	}
	
	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<GameModel> getGameEntity() {
		return gameEntity;
	}

	public void setGameEntity(List<GameModel> gameEntity) {
		this.gameEntity = gameEntity;
	}
	
	
	
	
}
