package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepositery;
import com.smart.entity.User;
import com.smart.helper.Message;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepositery userRepository;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	
	
	@RequestMapping("/about")
	public String about(Model model) {
		
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}
	
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User() );
		return "signup";
	}
	
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user,  @RequestParam(value = "agreement",defaultValue = "false") boolean agreement,Model model ,BindingResult result1,HttpSession session) {
		
		
	try {
		
	model.addAttribute("title", "Register - Smart Contact Manager");

		
		if(!agreement) {
			System.out.println("You have not agreed the terms adn conditions");
			throw new Exception("You have not agreed the terms adn conditions"); 
		}
		
		if (result1.hasErrors()) {
			
			System.out.println("Error +++++++++" + result1);
			model.addAttribute("user", user);
			 return "signup";
		}
		
		
		
		user.setImageUrl("default.png");
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	 User result = this.userRepository.save(user);
		
		
		
	model.addAttribute("user", new User());
	session.setAttribute("message", new Message("Successfully Registered" ,  "alert-success"));

		return "signup";
		
	}
	catch (Exception e) {

	e.printStackTrace();
	model.addAttribute("user", user); 
	session.setAttribute("message", new Message("Something Went Wrong!" + e.getMessage(), "alert-danger"));
	return "signup";

	}
		
	}
	
}
