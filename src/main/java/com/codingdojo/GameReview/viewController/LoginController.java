package com.codingdojo.GameReview.viewController;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.GameReview.auth.UserModel;
import com.codingdojo.GameReview.auth.UserValidator;
import com.codingdojo.GameReview.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

//	@GetMapping("/test")
//	public String index(Model modelView , HttpSession session) {
//		
//			modelView.addAttribute("registerForm", new UserModel());
//			modelView.addAttribute("loginForm", new UserLoginModel());
//			return "index.jsp";	
//	}
	
//	//-----------------------------Register Form------------------------------//	
//		@PostMapping("test/register")
//		public String registerUser(Model modelView,
//				@Valid @ModelAttribute("registerForm") UserModel userRegisterModel,
//				BindingResult result , RedirectAttributes redirectAttributes) {
//			
//			if(result.hasErrors()) {
//			
//				modelView.addAttribute("loginForm", new UserLoginModel());
//				return "index.jsp"; //with annotation error
//			}else {
//	
//				this.userService.crud_registerAccount(userRegisterModel , redirectAttributes);	
//				return "redirect:/";
//			}
//		}
//	
//		//-----------------------------Login Form------------------------------//
//		  @PostMapping("test/login") 
//		  public String loginUser(Model modelView, HttpSession session,
//		  @Valid @ModelAttribute("loginForm") UserLoginModel userLoginModel, BindingResult result , RedirectAttributes redirectAttributes){
//		  
//			
//			  
//			  if(result.hasErrors()) {
//					modelView.addAttribute("registerForm", new UserModel());
//					
//				  System.out.println("Error annotation constraint");
//				  return "index.jsp";
//			  }else if(userService.crud_emailValidation(userLoginModel.getLoginEmail()) == null){
//				  System.out.println("empty array");
//				  System.out.println(userService.crud_emailValidation(userLoginModel.getLoginEmail()));
//				  
//				  redirectAttributes.addFlashAttribute("loginEmailError", "Invalid Login");
//				  //if the email is not exist in the database
//				  return "redirect:/";
//			  
//			  }else if(!BCrypt.checkpw(userLoginModel.getLoginPassword(), userService.crud_emailValidation(userLoginModel.getLoginEmail()).getPassword())) {
//				  redirectAttributes.addFlashAttribute("loginEmailError", "Invalid Login");
//				  //password typed in login form is not equal to the password of the email in the database
//				  return "redirect:/";
//				  
//			  }else { 
//				  //success login
//				  UserModel userModel = this.userService.crud_emailValidation(userLoginModel.getLoginEmail());
//				  session.setAttribute("userIdSession", userModel.getId());
//				  return "redirect:/dashboard"; 
//			  }
//		  }
		  
		  //SPRING SECURITY VIA FORM
		    @GetMapping("/registration")
		    public String registerForm(@ModelAttribute("user") UserModel userModel) {
		        return "registrationPage.jsp";
		    }
		    
		 //Controller POST and Form:POST NEED to be the same URL as AUTHENTICATION @BEAN at WebSecurityConfig [.antMatchers()] otherwise, spring app will ignore any link and redirect to login page
		    @PostMapping("/registration")
		    public String registration(@Valid @ModelAttribute("user") UserModel userModel,
		    		BindingResult result, Model modelView, HttpSession session , RedirectAttributes redirectAttributes) {
		    	
		    	//conditional JSP (Bootstrap for register validation via spring security)
		    	modelView.addAttribute("userModel", userModel);
		    	
		    	//Register as USER	
		    	userValidator.validate(userModel, result);
		        if (result.hasErrors()) {

		            return "registrationPage.jsp";
		        }else {
		        	UserModel userNameDataChecker = this.userService.findByUsername(userModel.getUserName());
			        	if(userNameDataChecker != null) {
			        		
			        		redirectAttributes.addFlashAttribute("userNameDataCheckerMessage", "Username already exist");
			        		return "redirect:/registration";
			        	}else {
			        		redirectAttributes.addFlashAttribute("registrationMessage", "Account successfully created.");
				        	 userService.saveWithUserRole(userModel);
						     
				        	 return "redirect:/registration";
			        	}
		        }
		        
		       
		    }
		    
		    //Admin registration route
		    @GetMapping("/registration_admin")
		    public String registerAdminForm(@ModelAttribute("user") UserModel userModel) {
		        return "registrationPageAdmin.jsp";
		    }
		    
		    @PostMapping("/registration_admin")
		    public String registrationAdmin(@Valid @ModelAttribute("user") UserModel userModel,
		    		BindingResult result, Model modelView, HttpSession session , RedirectAttributes redirectAttributes) {
		    	
		    	//conditional JSP (Bootstrap for register validation via spring security)
		    	modelView.addAttribute("userModel", userModel);
		    	
		    	//Register as ADMIN
		    	userValidator.validate(userModel, result);
		    	if (result.hasErrors()) {
		    		
		    		return "registrationPageAdmin.jsp";
		        }else {
		        	UserModel userNameDataChecker = this.userService.findByUsername(userModel.getUserName());
		        	if(userNameDataChecker != null) {
		        		
		        		redirectAttributes.addFlashAttribute("userNameDataCheckerMessage", "Username already exist");
		        		return "redirect:/registration_admin";
		        	}else {
		        		redirectAttributes.addFlashAttribute("registrationMessage", "Account successfully created.");
				        userService.saveUserWithAdminRole(userModel);
				        return "redirect:/registration_admin";
		        	}

		        }
		    }
		    
		    
		    //Invokes loadUserByUsername() from UserDetailsService class.
		    @GetMapping("/login")
		    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		        
		    	//Spring Security redirect the client to the /login?error url
		    	if(error != null) {
		            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		        }
		    	
		    	//Spring Security redirects them to /login?logout url
		        if(logout != null) {
		            model.addAttribute("logoutMessage", "Logout Successful!");
		        }
		        return "loginPage.jsp"; 
		     
		    }
		    
		    //Successful login via Spring Security (Using Principal Built-in class)
//		    @GetMapping(value = {"/", "/dashboard"})
//		    public String user_index(Principal principal, Model model) {
//		        // 1 - TO load Username on the /dashboard page
//		        String username = principal.getName();
//		        model.addAttribute("currentUser", userService.findByUsername(username));
//		        
//		        //You can put this on different controller to separate
//		        
//		        return "dashboard.jsp";
//		    }
//		    
		
	
}

