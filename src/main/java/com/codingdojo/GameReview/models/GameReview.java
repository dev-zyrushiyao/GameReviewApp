package com.codingdojo.GameReview.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.codingdojo.GameReview.auth.UserModel;

@Entity
@Table(name = "game_review")
public class GameReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "This field should not be blank")
	@Size(min=1 , max=100, message="Error! Only 100 max characters for comments")
	private String comment;
	
	@Min(1)
	@Max(5)
	@NotNull(message = "This field should not be blank")
	private Integer rating;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="game_id")
	private GameModel gameEntity;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserModel userEntity;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
	//super constructor
	public GameReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//field constructor
	public GameReview(@NotEmpty(message = "This field should not be blank") String comment,
			@NotNull(message = "This field should not be blank") Integer rating, GameModel gameEntity,
			UserModel userEntity) {
		super();
		this.comment = comment;
		this.rating = rating;
		this.gameEntity = gameEntity;
		this.userEntity = userEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public GameModel getGameEntity() {
		return gameEntity;
	}

	public void setGameEntity(GameModel gameEntity) {
		this.gameEntity = gameEntity;
	}

	public UserModel getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserModel userEntity) {
		this.userEntity = userEntity;
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
	
	
	
}	
	

	