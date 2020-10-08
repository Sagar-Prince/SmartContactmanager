package com.smart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepositery;
import com.smart.entity.User;
import com.smart.helper.Message;

@Controller
public class HomeController {
	@Autowired
	private UserRepositery userRepositery;

	@GetMapping("/")

	public String homeController(Model model) {
		model.addAttribute("title", "home - smart Contect manager");
		return "home";
	}

	@GetMapping("/about")

	public String about(Model model) {
		model.addAttribute("title", "about- smart Contect manager");
		return "about";
	}

	@GetMapping("/signup")

	public String signup(Model model) {
		model.addAttribute("title", "Register- smart Contect manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	// this is handler for registering user
	@PostMapping("/do_register")

	public String registerUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		model.addAttribute("title", "success- smart Contect manager");
		try {
			if (agreement == false) {

				System.out.println("you have not agreed term and condation.");
				throw new Exception("you have not agreed term and condation.");
			}
			user.setRole("role_User");
			user.setEnabled(true);

			User result = this.userRepositery.save(user);

			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully registered !!", "alert-success"));

			return "signup";

		  } catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("somthing went wrong " + e.getMessage(), "alert-danger"));
			return "signup";

		}

	}

}
