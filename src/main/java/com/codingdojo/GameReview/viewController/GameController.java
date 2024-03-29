package com.codingdojo.GameReview.viewController;




import java.security.Principal;
import java.util.Collections;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.eclipse.jdt.internal.compiler.flow.LoopingFlowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.GameReview.auth.UserModel;
import com.codingdojo.GameReview.models.GameGenreModel;
import com.codingdojo.GameReview.models.GameModel;
import com.codingdojo.GameReview.models.GamePlatformModel;
import com.codingdojo.GameReview.models.GameReview;
import com.codingdojo.GameReview.services.GameService;
import com.codingdojo.GameReview.services.GenreService;
import com.codingdojo.GameReview.services.PlatformService;
import com.codingdojo.GameReview.services.ReviewService;
import com.codingdojo.GameReview.services.UserService;



@Controller
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private PlatformService platformService;
	

	@GetMapping(value = {"/", "/dashboard"})
	public String dashboardPage(Principal principal,  HttpServletRequest request, HttpSession session, Model modelView) {
		// 1 - TO load Username on the /dashboard page
        String username = principal.getName();
        modelView.addAttribute("currentUser", userService.findByUsername(username));
        
       
			
			//dropdown-genre
			List<GameGenreModel> genreList = this.genreService.findAllGenre();
			modelView.addAttribute("genreList", genreList);
			
			//dropdown-platform
			List<GamePlatformModel> platformList = this.platformService.findAllPlatform();
			modelView.addAttribute("platformList", platformList);
			
//			//title search
//			if(session.getAttribute("titleListSession") != null) {
//				List<GameModel> titleList = this.gameService.findTitle(session.getAttribute("titleListSession").toString());
//				modelView.addAttribute("titleList" , titleList);
//			}
			
//			//------<Genre Filter>----//
//			if(session.getAttribute("genreValueSession") == null || session.getAttribute("genreValueSession").equals("0")) {
//				Long genreId = 0L;
//				System.out.println("genreValueSession is equal to 0 or null , reload data in the table again");
//			}else {
//				Long genreId = Long.parseLong((String)session.getAttribute("genreValueSession"));
//				GameGenreModel gameGenreModel = this.genreService.findGenreId(genreId);
//				List<GameModel> filteredList = this.gameService.findGenreEntity(gameGenreModel);
//
//				modelView.addAttribute("genreId" , genreId);
//				modelView.addAttribute("filteredList", filteredList);
//				//------</Genre Filter>----//
//			}
			
	
			//------Filter<Genre , Platform>----// -- Add conditional to JSP as well (IF's of controller and JSP in order)
			//---if session is not yet existed thus object is NULL
			if( 
				((session.getAttribute("genreValueSession")) == null) 
				&& 
				((session.getAttribute("platformValueSession")) == null)
				){
				
				//table dropdown-ALL
				List<GameModel> gameList = this.gameService.findAll();
				modelView.addAttribute("gameList", gameList);
				
			//---if session dropdown value is both ALL (ZERO)	
			}else if(
					((session.getAttribute("genreValueSession")).equals("0")) 
					&& 
					((session.getAttribute("platformValueSession")).equals("0"))
					){
				
				//table dropdown-ALL
				List<GameModel> gameList = this.gameService.findAll();
				modelView.addAttribute("gameList", gameList);
				
				System.out.println("Both value is 0");
			
			//---if session dropdown genre is not Equal to All + dropdown platform is equals to ALL (0)	
			}else if(
					(!(session.getAttribute("genreValueSession")).equals("0")) 
					&& 
					((session.getAttribute("platformValueSession")).equals("0"))
					){
				
				GameGenreModel typeOfGenre = this.genreService.findGenreId(Long.parseLong(session.getAttribute("genreValueSession").toString()));
				modelView.addAttribute("filterByInfo", typeOfGenre.getGenre());
				 
				Long genreId = Long.parseLong((String)session.getAttribute("genreValueSession"));
				GameGenreModel gameGenreModel = this.genreService.findGenreId(genreId);
				List<GameModel> filteredList = this.gameService.findGenreEntity(gameGenreModel);

				modelView.addAttribute("genreId" , genreId); //JSP Comparisson to session genre
				modelView.addAttribute("gameList", filteredList);
				
			
			//---if session dropdown genre is not Equal to All + dropdown platform is equals to ALL (0)	
			}else if(
					((session.getAttribute("genreValueSession")).equals("0")) 
					&& 
					(!(session.getAttribute("platformValueSession")).equals("0"))
					){
				
				GamePlatformModel typeOfPlatform = this.platformService.findPlaformId(Long.parseLong(session.getAttribute("platformValueSession").toString()));
				modelView.addAttribute("filterByInfo", typeOfPlatform.getPlatformName());
				
				Long platformId = Long.parseLong((String)session.getAttribute("platformValueSession"));
				GamePlatformModel gamePlatformModel = this.platformService.findPlaformId(platformId);
				List<GameModel> filteredList = this.gameService.findPlatformEntity(gamePlatformModel);

				modelView.addAttribute("platformId" , platformId); //JSP Comparisson to session genre
				modelView.addAttribute("gameList", filteredList);
			}else {
				GameGenreModel typeOfGenre = this.genreService.findGenreId(Long.parseLong(session.getAttribute("genreValueSession").toString()));
				GamePlatformModel typeOfPlatform = this.platformService.findPlaformId(Long.parseLong(session.getAttribute("platformValueSession").toString()));
				modelView.addAttribute("filterByInfo", typeOfGenre.getGenre() + " , " +  typeOfPlatform.getPlatformName());
				
				//filter using 2 dropdown - GET Load page / GET search route / GET LOGOUT
				Long genreId = Long.parseLong((String)session.getAttribute("genreValueSession"));
				Long platformId = Long.parseLong((String)session.getAttribute("platformValueSession"));
			
				GameGenreModel gameGenreModel = this.genreService.findGenreId(genreId);
				GamePlatformModel gamePlatformModel = this.platformService.findPlaformId(platformId);
				List<GameModel> filterGenrePlatform = this.gameService.filterGenrePlatform(gameGenreModel, gamePlatformModel);
	
				modelView.addAttribute("gameList", filterGenrePlatform);
			}
				
			return "dashboard.jsp";	
	}
	
	
	//Dashboard to be continued on index
	@GetMapping("/search/")
	public String searchFilter(Model modelView, HttpServletRequest request , HttpSession session) {
		
		//request Parameter works on FORM and does not get the actual value without submit button, returns to null if not on form
		session.setAttribute("genreValueSession" , request.getParameter("genre_value"));
		session.setAttribute("platformValueSession", request.getParameter("platform_value"));
		
		return "redirect:/";
	}
	
	

// LOGOUT was removed because it has already built-in logout using spring security
//	@GetMapping("/logout")
//	public String logoutUser(HttpSession session) {
//		if(session.getAttribute("userIdSession") == null) {
//			return "redirect:/";
//		}else {
//			//filter dropdown session
//			session.removeAttribute("genreValueSession");
//			session.removeAttribute("platformValueSession");
//			
//			session.removeAttribute("userIdSession");
//			return "redirect:/";
//		}		
//	}
	
	@GetMapping("/admin/commands")
	public String adminCommandPage(Model modelView , Principal principal) {
		String username = principal.getName();
		modelView.addAttribute("currentUser", userService.findByUsername(username));
		
		return "admin_commands.jsp";
	}
	
	
	
	@GetMapping("/admin/new/game")
	public String addGamePage(Model modelView , HttpServletRequest request , HttpSession session) {
		modelView.addAttribute("gameForm" , new GameModel());
			
		List<GameGenreModel> genreData = this.genreService.findAllGenre();
		List<GamePlatformModel> platformData = this.platformService.findAllPlatform(); 
			
		//Dropdown
		  modelView.addAttribute("genreList", genreData);
		  modelView.addAttribute("platformList", platformData); 
		  
		if(genreData.isEmpty() && platformData.isEmpty()) {
			modelView.addAttribute("gameWarning", "Warning: No Genre/Platform data was found; please create them first before able to create game to the list");
			System.out.println("1");
		}else if (genreData.isEmpty()) {
			modelView.addAttribute("gameWarning", "Warning: No Genre data was found; please create them first before able to create game on the list");
			System.out.println("2");
		}else if (platformData.isEmpty()) {
			modelView.addAttribute("gameWarning", "Warning: No Platform data was found; please create them first before able to create game on the list");
			System.out.println("3");
		}
	
		return "admin_createGame.jsp";
	}
	
	@PostMapping("/admin/post/new/game")
	public String addGame(Model modelView, RedirectAttributes redirectAttributes, HttpServletRequest request,
			@Valid @ModelAttribute("gameForm") GameModel gameModel , BindingResult result) {
		
		if(result.hasErrors()) {
			List<GameGenreModel> genreData = this.genreService.findAllGenre();
			List<GamePlatformModel> platformData = this.platformService.findAllPlatform(); 
				
			//Dropdown
			  modelView.addAttribute("genreList", genreData);
			  modelView.addAttribute("platformList", platformData); 
			  
			if(genreData.isEmpty() && platformData.isEmpty()) {
				modelView.addAttribute("gameWarning", "Warning: No Genre/Platform data was found; please create them first before able to create game to the list");
		
			}else if (genreData.isEmpty()) {
				modelView.addAttribute("gameWarning", "Warning: No Genre data was found; please create them first before able to create game on the list");

			}else if (platformData.isEmpty()) {
				modelView.addAttribute("gameWarning", "Warning: No Platform data was found; please create them first before able to create game on the list");
	
			}
		
			return "admin_createGame.jsp";
		}else {
			if(gameModel.getTrailerUrl().contains("youtu.be") || gameModel.getTrailerUrl().contains("youtube")) {
				
				redirectAttributes.addFlashAttribute("gameCreateMessageError", "ERROR: [https://youtu.be/] is found, this parameter only accept video ID, see the Trailer URL tooltip for example");
				return "redirect:/admin/new/game";
			}else if(gameModel.getGenreEntity() == null || gameModel.getPlatformEntity() == null){
				redirectAttributes.addFlashAttribute("gameCreateMessageError", "ERROR: [Genre/Platform] is null \n please create Genre/Platform data via Admin Controls first");
				return "redirect:/admin/new/game";
			}else if(!this.gameService.findTitle(gameModel.getTitle()).isEmpty()) {
				redirectAttributes.addFlashAttribute("gameCreateMessageError", "ERROR: Game already exist on the list");
				return "redirect:/admin/new/game";
			}else {
				this.gameService.createGame(gameModel);
				redirectAttributes.addFlashAttribute("gameCreateMessage", "Game Added successfully!");
				
				return "redirect:/admin/new/game";
			}
		}
	}
	
	@GetMapping("/admin/update/game/{id}")
	public String updateGamePage(Model modelView , @PathVariable long id) {
		
		  GameModel gameModel = this.gameService.findGameId(id);
		  modelView.addAttribute("gameInfo" , gameModel);
		  modelView.addAttribute("updateGameForm", gameModel);
		  
		  modelView.addAttribute("genreList", this.genreService.findAllGenre());
		  modelView.addAttribute("platformList", this.platformService.findAllPlatform()); 
		 
		return "admin_updateGame.jsp";
	}
	
	@PutMapping("/admin/update/game/info/{id}")
	public String updateGameData(Model modelView, RedirectAttributes redirectAttributes, @PathVariable long id,
			@Valid @ModelAttribute("updateGameForm") GameModel gameModel , BindingResult result) {
			
		if (result.hasErrors()) {
			  GameModel gameModelInfo = this.gameService.findGameId(id);
			  modelView.addAttribute("gameInfo" , gameModelInfo);
			
			  modelView.addAttribute("genreList", this.genreService.findAllGenre());
			  modelView.addAttribute("platformList", this.platformService.findAllPlatform()); 
			
			return "admin_updateGame.jsp";
		}else {
			if(gameModel.getTrailerUrl().contains("youtu.be") || gameModel.getTrailerUrl().contains("youtube") ) {
				redirectAttributes.addFlashAttribute("gameUpdateError", "Full video URL has been detected, please only put youtube ID");
				return "redirect:/admin/update/game/"+id;	
			}else {
				this.gameService.updateGame(gameModel);
				return "redirect:/view/game/info/id/"+id;	
			}
		}
	}
	
	@GetMapping("/admin/delete/game/id/{id}")
	public String deleteGame(@PathVariable Long id) {	
		
		GameModel gameModel = this.gameService.findGameId(id);
		List<UserModel> userBookmarks = gameModel.getUserBookmark();
		
			//LOOP Sequence to remove all bookmark users on a game
			for (int i=0 ; i < userBookmarks.size() ; i++) {
				UserModel userBooked = this.userService.findByUsername(userBookmarks.get(i).getUserName());
				this.gameService.userRemoveBookmark(userBooked, gameModel);
			}
			
			//delete the game after removing users bookmark of game (including comments[cascade all])
			this.gameService.deleteGameId(id);
		
		return "redirect:/";
	}
	
	//Bookmark Game - ADD
	@GetMapping("/bookmark/add/game/id/{id}")
	public String bookmarkGameAdd(@PathVariable Long id, Principal principal) {
		
		String username = principal.getName();
		UserModel currentUser = this.userService.findByUsername(username);
		
		GameModel gameModel = this.gameService.findGameId(id);
	
		this.gameService.userAddBookmark(currentUser, gameModel);
		
		//Conditional statement for Bookmark
//		System.out.println(currentUser.getGames().contains(gameModel));
		
		return "redirect:/view/game/info/id/" + gameModel.getId();
	}
	
	//Bookmark Game - REMOVE
	@GetMapping("/bookmark/remove/game/id/{id}")
	public String bookmarkGameRemove(@PathVariable Long id, Principal principal) {
		
		String username = principal.getName();
		UserModel currentUser = this.userService.findByUsername(username);
		
		GameModel gameModel = this.gameService.findGameId(id);
		
		this.gameService.userRemoveBookmark(currentUser, gameModel);
		
		return "redirect:/view/game/info/id/" + gameModel.getId();
	}
	
	@GetMapping("/view/game/info/id/{id}")
	public String viewGameInfo(Principal principal , Model modelView , HttpSession session,
			@PathVariable Long id,
			@ModelAttribute ("reviewForm") GameReview gameReview) {
	        	
	        //User Data for comments
			String username = principal.getName();
			UserModel currentUser = userService.findByUsername(username);
	        modelView.addAttribute("currentUser", currentUser); //object getters to be invoked in JSP
		
	    //form
		modelView.addAttribute("reviewForm", gameReview);
		
		//Game Data to retrieve game information
		GameModel gameModel = this.gameService.findGameId(id);
		modelView.addAttribute("gameInfo", gameModel);
		session.setAttribute("gameIdSession" , gameModel.getId()); //to use on review postmapping
		
		
		//displaying comments (decending order) via which GameModel (name) //gamemodel Object used already invoked findGameId()
		List<GameReview> userReview = this.reviewService.findGameEntityComment(gameModel);
		Collections.reverse(userReview);
		modelView.addAttribute("commentList",userReview);

		return "viewGame.jsp";
	}
	
	
	@GetMapping("/search/rating/{id}")
	public String searchRating(Principal principal , Model modelView, @PathVariable Long id , HttpServletRequest request, HttpSession session) {
		 //User Data for comments
		String username = principal.getName();
		UserModel currentUser = userService.findByUsername(username);
        modelView.addAttribute("currentUser", currentUser); 
		
        //Review Form
		modelView.addAttribute("reviewForm", new GameReview());
		
		GameModel gameModel = this.gameService.findGameId(id);
		String ratingFilter = request.getParameter("rating-filter");
		Integer gameRating = Integer.parseInt(ratingFilter);
		String pageTarget = "#game-comment";
		
		if(!ratingFilter.equals("6")) {
			List<GameReview> gameReview = this.reviewService.FindCommentsRatingOfGame(gameRating , gameModel);
			modelView.addAttribute("commentList", gameReview);
			
			modelView.addAttribute("gameInfo", gameModel);
			
			return "viewGame.jsp"; //on FORM JSP it has pageTarget on action to prevent page from loading at the top.
		}else {
			List<GameReview> gameReview = this.reviewService.findGameEntityComment(gameModel);
			modelView.addAttribute("commentList", gameReview);
		
			return "redirect:/view/game/info/id/" + id + pageTarget;	
		}
	}
	
	@GetMapping("/admin/update/review/{id}")
	public String updateReviewAsAdminPage(HttpSession session, @PathVariable Long id , Model modelView) {
		modelView.addAttribute("updateReviewForm", this.reviewService.findReviewId(id));
		
		GameReview gameReview = this.reviewService.findReviewId(id);
		modelView.addAttribute("gameReview", gameReview);

		
		//return URL route of Delete Review to View Game
		session.setAttribute("reviewGameEntity", this.reviewService.findReviewId(id).getGameEntity().getId());
		
		return "admin_updateReview.jsp";
	}
	
	@PutMapping("/admin/update/review/info/{id}")
	public String updateReviewAsAdmin(Model modelView, @PathVariable Long id, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("updateReviewForm") GameReview gameReview , BindingResult result) {
		
		if(result.hasErrors()) {
			modelView.addAttribute("gameReview", this.reviewService.findReviewId(id));
			return "admin_updateReview.jsp";
		}else {
			
			redirectAttributes.addFlashAttribute("reviewUpdateMessage", "Review has been updated!");
			this.reviewService.updateReview(gameReview);
			
			return "redirect:/view/game/info/id/" + gameReview.getGameEntity().getId();
		}
	}
	
	//delete comments
	@GetMapping("/admin/delete/review/{id}")
	public String deleteReviewAsAdmin(@PathVariable Long id) {
		
		//PathVariable id = review ID
		System.out.println(id);
		
		//BUG:Cannot invoke "java.util.function.Supplier.get()" because "supplier" is null
		//this.reviewService.deleteReviewId(id);
		
		//ID trigger to return on Game View 
		//(review service culprit triggering the [supplier null error] because we are invoking the delete method then searching for its review ID
		//workaround: invoke delete method after this instance)
		GameReview gameReviewRoute = this.reviewService.findReviewId(id);
		
		//invoking delete method
		this.reviewService.deleteReviewId(id);
	
		return "redirect:/view/game/info/id/" + gameReviewRoute.getGameEntity().getId() + "#game_review_title";
	}
	
	
	@PostMapping("/post/review")
	public String addReview(Principal principal , Model modelView, HttpSession session,
			@Valid @ModelAttribute ("reviewForm") GameReview gameReview , BindingResult result
			) {
		
			//removed the pathvariable to avoid the bug updating a single comment for all user
			if(result.hasErrors()) {
				String username = principal.getName();
				UserModel currentUser = userService.findByUsername(username);
		        modelView.addAttribute("currentUser", currentUser); 
				
				//Game Data to retrieve game information
				GameModel gameModel = this.gameService.findGameId((Long)session.getAttribute("gameIdSession"));
				modelView.addAttribute("gameInfo", gameModel);
				
				List<GameReview> userReview = this.reviewService.findGameEntityComment(gameModel);
				Collections.reverse(userReview);
				session.getAttribute("userReview");
				modelView.addAttribute("commentList",userReview);
				return "viewGame.jsp";
			}else {
				
				this.reviewService.createReview(gameReview);
				return "redirect:/view/game/info/id/" + session.getAttribute("gameIdSession");
			}
	}
	
	
	@GetMapping("/admin/new/genre")
	public String addGenrePage(Model modelView) {
		modelView.addAttribute("genreForm", new GameGenreModel());
		return "admin_createGenre.jsp";
	}
	
	@PostMapping("/admin/add/genre")
	public String createGenre(RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("genreForm") GameGenreModel gameGenreModel , BindingResult result) {
		if(result.hasErrors()) {
			return "admin_createGenre.jsp";
		}else {
			List<GameGenreModel> genreDataChecker = this.genreService.findGenre(gameGenreModel.getGenre());
			if(!genreDataChecker.isEmpty()) {
				redirectAttributes.addFlashAttribute("genreError", "Genre already exist");
				return "redirect:/admin/new/genre";
			}else {
				redirectAttributes.addFlashAttribute("genreMessage", "Genre has been successfully added!");
				this.genreService.createGenre(gameGenreModel);
				return "redirect:/admin/new/genre";
			}
			
		}
	}
	

	//Pagination-GENRE
	//@GetMapping("/genre/pagination/{pageTarget}/{pageSize}")
	@GetMapping("/admin/view/list/genre/page/{pageTarget}")
	public String paginationGenre(Model modelView ,@PathVariable int pageTarget) {
		
		// default data size per page
		int pageSize = 10; 
		PageRequest pageRequest = PageRequest.of(pageTarget, pageSize);
			
		Page<GameGenreModel> gameGenreModel = this.genreService.findAllPage(pageRequest);
		modelView.addAttribute("listOfGenre" , gameGenreModel.getContent()); //.getContent returns the data as List of Iteration of JSP ; w/o it spring boot will throw an error JspTagException:[Don't know how to iterate over supplied "items" in &lt;forEach&gt;]
		modelView.addAttribute("totalPages", gameGenreModel.getTotalPages());
		modelView.addAttribute("currentPage", gameGenreModel.getNumber());
		
		//For each of PAGE (not returned as List) works in backend
//		for(GameGenreModel gameGenreContent : gameGenreModel) {
//			System.out.println("page " + pageTarget + ": " + gameGenreContent.getGenre());
//		}
			
		return "admin_viewAllGenre.jsp";
	}
	
	@GetMapping("/admin/update/genre/id/{id}")
	public String updateGenrePage(Model modelView , @PathVariable Long id) {
		
		GameGenreModel gameGenreModel = this.genreService.findGenreId(id);
		modelView.addAttribute("genreForm", gameGenreModel);
		
		modelView.addAttribute("gameGenreModel", gameGenreModel);
		
		return "admin_updateGenre.jsp";
	}
	
	@PutMapping("/admin/update/info/genre/id/{id}")
	public String updateGenreInfo(Model modelView , @PathVariable Long id, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("genreForm") GameGenreModel genreModel , BindingResult result) {
		
		if(result.hasErrors()) {
			//reload the view again
			GameGenreModel gameGenreModel = this.genreService.findGenreId(id);
			
			modelView.addAttribute("gameGenreModel", gameGenreModel);
			return "admin_updateGenre.jsp";
		}else {
			GameGenreModel gameGenreModel = this.genreService.findGenreId(id);
			String genreMessage = ("Genre ID:" + gameGenreModel.getId() + " has been updated!");
			redirectAttributes.addFlashAttribute("genreMessage", genreMessage);
			
			this.genreService.updateGenre(genreModel);
			return "redirect:/admin/update/genre/id/" + id;
		}		
	}
	

	@GetMapping("/admin/delete/genre/{id}")
	public String deleteGenre(@PathVariable Long id , RedirectAttributes redirectAttributes) {
		
		try {
			this.genreService.deleteGenreById(id);
			return "redirect:/admin/view/list/genre";
		}catch(Exception e) {
			//org.springframework.dao.DataIntegrityViolationException: could not execute statement; 
			//Cannot delete a platform when used by Game because Game has relation to both (Genre and Platform)
			
			String errorMsg = "ERROR: Cannot delete a platform when used by Games because Game has relation to both (Genre and Platform); please delete GAMES that has same genre/platform";
			redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
			
			return "redirect:/admin/view/list/genre";
		}
	}
	
	
	
	
	@GetMapping("/admin/new/platform")
	public String addPlatformPage (Model modelView) {
		modelView.addAttribute("platformForm", new GamePlatformModel());
		
		return "admin_createPlatform.jsp";
	}
	
	@PostMapping("/admin/add/platform")
	public String createPlatform(Model modelView, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("platformForm") GamePlatformModel gamePlatformModel , BindingResult result) {
		
		if(result.hasErrors()) {
			return "admin_createPlatform.jsp";
		}else {
			List<GamePlatformModel> platformDataChecker = this.platformService.findPlatformName(gamePlatformModel.getPlatformName());
			if(!platformDataChecker.isEmpty()) {
				redirectAttributes.addFlashAttribute("platformMessageError", "Platform already exist");
				return "redirect:/admin/new/platform";
			}else {
				redirectAttributes.addFlashAttribute("platformMessage", "Platform has been successfully added!");
				this.platformService.createPlatform(gamePlatformModel);
				return "redirect:/admin/new/platform";
				
			}
		}
	}
	
	//Pagination Platform	
	@GetMapping("/admin/view/list/platform/page/{pageTarget}")
	public String paginationPlatform(Model modelView , @PathVariable int pageTarget) {
		
			// default data size per page
			int pageSize = 10; 
			PageRequest pageRequest = PageRequest.of(pageTarget, pageSize);
				
			Page<GamePlatformModel> gamePlatformModel = this.platformService.findAllPage(pageRequest);
			modelView.addAttribute("listOfPlatform" , gamePlatformModel.getContent()); //.getContent returns the data as List of Iteration of JSP ; w/o it spring boot will throw an error JspTagException:[Don't know how to iterate over supplied "items" in &lt;forEach&gt;]
			modelView.addAttribute("totalPages", gamePlatformModel.getTotalPages());
			modelView.addAttribute("currentPage", gamePlatformModel.getNumber());
			
			//For each of PAGE (not returned as List) works in backend
//			for(GamePlatformModel gamePlatformContent : gamePlatformModel) {
//				System.out.println("page " + pageTarget + ": " + gamePlatformContent.getGenre());
//			}
		
		return "admin_viewAllPlatform.jsp";
	}
	
	@GetMapping("/admin/update/platform/id/{id}")
	public String updatePlatformPage(Model modelView , @PathVariable Long id) {
		modelView.addAttribute("platformForm", this.platformService.findPlaformId(id));
		
		modelView.addAttribute("gamePlatformModel", this.platformService.findPlaformId(id));
		return "admin_updatePlatform.jsp";
	}
	
	@PutMapping("/admin/update/info/platform/id/{id}")
	public String updatePlatformInfo(Model modelView , @PathVariable Long id, RedirectAttributes redirectAttributes,
		@Valid @ModelAttribute("platformForm") GamePlatformModel platformModel , 
		BindingResult result) {
		
		if(result.hasErrors()) {
			GamePlatformModel gamePlatformModel = this.platformService.findPlaformId(id);
			
			modelView.addAttribute("gamePlatformModel",gamePlatformModel);
			
			return "admin_updatePlatform.jsp";
		}else {
			String platformMessage = ("Platform ID:" +  this.platformService.findPlaformId(id).getId() + " has been updated!");
			redirectAttributes.addFlashAttribute("platformMessage", platformMessage);
			
			this.platformService.updatePlatform(platformModel);
			return "redirect:/admin/update/platform/id/" + id;
		}
	}
	

	
	@DeleteMapping("/admin/delete/platform/{id}")
	public String deletePlatform(@PathVariable Long id , RedirectAttributes redirectAttributes) {
		
		try {
			this.platformService.deletePlatform(id);
			return "redirect:/admin/view/list/platform";
		}catch(Exception e) {
			//org.springframework.dao.DataIntegrityViolationException: could not execute statement; 
			//Cannot delete a platform when used by Game because Game has relation to both (Genre and Platform)
			
			String errorMsg = "ERROR: Cannot delete a platform when used by Games because Game has relation to both (Genre and Platform); please delete GAMES that has same genre/platform";
			redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
			
			return "redirect:/admin/view/list/platform";
		}
			
	}
	
	//Bookmarks
	@GetMapping("/view/bookmark")
	public String viewBookmarkPage(Principal principal , Model modelView) {
		
		String username = principal.getName();
		UserModel currentUser = userService.findByUsername(username);
        modelView.addAttribute("currentUser", currentUser); 
        
        modelView.addAttribute("userBookmark", currentUser.getGames());
		
		return "user_bookmark.jsp";
	}
	
	@GetMapping("/remove/bookmarklist/{id}")
	public String removeBookmarksPage(Principal principal, @PathVariable Long id) {
		String username = principal.getName();
		UserModel currentUser = this.userService.findByUsername(username);
		
		GameModel gameModel = this.gameService.findGameId(id);
		
		this.gameService.userRemoveBookmark(currentUser, gameModel);
		
		return "redirect:/view/bookmark";
	}
		
	
	
	
	
}
