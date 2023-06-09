package com.codingdojo.GameReview.models;

import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import org.springframework.format.annotation.DateTimeFormat;

import com.codingdojo.GameReview.auth.UserModel;





@Entity
@Table(name = "game_info")
public class GameModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "This field should not be blank")
	private String title;
	@NotBlank(message = "This field should not be blank")
	private String imageUrl;
	@NotBlank(message = "This field should not be blank")
	private String trailerUrl;
	
	
	@OneToMany(mappedBy = "gameEntity" , fetch = FetchType.LAZY)
	private List<GameReview> reviewEntity;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name="platform_id")
	private GamePlatformModel platformEntity;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name="genre_id")
	private GameGenreModel genreEntity;
	
   
    @ManyToMany(mappedBy = "games")
    private List<UserModel> userBookmark;	
    
    
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
	public GameModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//field constructor
	public GameModel(@NotEmpty(message = "This field should not be blank") String title,
			@NotBlank(message = "This field should not be blank") String imageUrl,
			@NotBlank(message = "This field should not be blank") String trailerUrl, List<GameReview> reviewEntity,
			GamePlatformModel platformEntity, GameGenreModel genreEntity) {
		super();
		this.title = title;
		this.imageUrl = imageUrl;
		this.trailerUrl = trailerUrl;
		this.reviewEntity = reviewEntity;
		this.platformEntity = platformEntity;
		this.genreEntity = genreEntity;
	}
	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
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
	
	// Many To Many
	public List<UserModel> getUserBookmark() {
		return userBookmark;
	}

	public void setUserBookmark(List<UserModel> userBookmark) {
		this.userBookmark = userBookmark;
	}

	
	// One to Many
	public List<GameReview> getReviewEntity() {
		return reviewEntity;
	}
	
	public void setReviewEntity(List<GameReview> reviewEntity) {
		this.reviewEntity = reviewEntity;
	}
	
	// One to Many
	public GamePlatformModel getPlatformEntity() {
		return platformEntity;
	}
	
	public void setPlatformEntity(GamePlatformModel platformEntity) {
		this.platformEntity = platformEntity;
	}
	
	// One to Many
	public GameGenreModel getGenreEntity() {
		return genreEntity;
	}
	
	public void setGenreEntity(GameGenreModel genreEntity) {
		this.genreEntity = genreEntity;
	}
	
	
}