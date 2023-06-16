package com.codingdojo.GameReview.auth;

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

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.codingdojo.GameReview.models.GameModel;

@Entity
@Table(name = "user_account")
public class UserModel {
	
		//Before you create an account
			//Inject command below on MySQL WorkBench ROLE TABLE (double click schema first) or make API services to add Roles via POSTMAN 
			//INSERT INTO roles (name) VALUES ('ROLE_USER');
			//INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
		
	
	  	@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	  	
	  	@Size(min=3, message="Username must be greater than 3 characters")
	    private String userName;
	    
	  	@Size(min=5, message="Password must be greater than 5 characters")
	    private String password;
	    
	    @Transient
	    private String passwordConfirmation;
	    
	    @Column(updatable=false)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date updatedAt;
	    
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
	    
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "users_roles", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private List<UserRoleModel> roles;
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "users_bookmark", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "game_id"))
	    private List<GameModel> games;
	    
	
		public UserModel() {
	    }
		public UserModel(String userName, String password, String passwordConfirmation) {
			super();
			this.userName = userName;
			this.password = password;
			this.passwordConfirmation = passwordConfirmation;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}
		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
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
		public List<UserRoleModel> getRoles() {
			return roles;
		}
		public void setRoles(List<UserRoleModel> roles) {
			this.roles = roles;
		}
	    
		// Many To Many
		public List<GameModel> getGames() {
			return games;
		}
		public void setGames(List<GameModel> games) {
			this.games = games;
		}
	    
	    
	
	
	
	
		
	
	
}
