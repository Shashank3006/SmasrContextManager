package com.smart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.entity.User;
import com.smart.helper.Message;
import com.smart.repositry.UserRepositry;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeContr {
	@Autowired
	private UserRepositry repo;

	@GetMapping("/")
	public String test(Model model) {
		model.addAttribute("s", "Home");

		return "home";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("s", "About");
		model.addAttribute("ts", "About");

		return "about";
	}

	@RequestMapping()
	public String login() {
		return "login";
	}

	@RequestMapping("/singup")
	public String singup(Model model) {
		model.addAttribute("s", "Sing up");
		model.addAttribute("user", new User());

		return "singup";
	}

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registeruser(@Valid @ModelAttribute("user") User user,BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,HttpSession session) 
	{
		try {
			if(!agreement) 
			{
				System.out.println("terms ");
				throw new Exception("you have not agery to terms and condision");
			}
			System.out.println(agreement);
			System.out.println(user);
			User us=repo.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully register","alert-success"));
			return "singup";
		} catch (Exception e) {
			// TODO: handle exception
		
		e.printStackTrace();
		model.addAttribute("user",user);
		session.setAttribute("message", new Message("Somthing went wrong!!"+e.getMessage(), "alert-warning"));
		return "singup";
		}

		
	}

}
