package com.web.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.entity.User;
import com.web.repository.UserRepository;


@Controller
public class UserController {
	
	@Autowired(required = true)
	UserRepository repo;
	
	
	
	@GetMapping("/")
	public String index() {
		return "Index";
	}
	

	@GetMapping("Sign")
	public String sign() {
		return "Signup";
	}
	
	@GetMapping("home")
	public String home() {
		return "Home";
	}
	
	
	@GetMapping("Login")
	public String login(Model model) {
		User user= new User();
		model.addAttribute("user",user);
		
		return "Login";
	}
	
	@GetMapping("Profile")
	public String profile() {
		return "ProfilePage";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute User u , HttpSession session) {
		
		 
			 repo.save(u);
				session.setAttribute("message", "User SignUp Succesfully...");
		 
		
		return "redirect:Sign";
		
	}
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("user" ) User user) {
		
		
		String useremail= user.getEmail();
		User userdata= this.repo.findByEmail(useremail);
		if(user.getPassword().equals(userdata.getPassword())) {
			return "redirect:home";
		}else {
			
		}
		return "error";
	
	
}
	
}
