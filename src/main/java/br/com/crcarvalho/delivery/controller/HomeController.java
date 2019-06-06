package br.com.crcarvalho.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		return "home/index";
	}
	
	@GetMapping("/ambiente")
	public String ambiente() {
		return "home/ambiente";
	}
	
	@GetMapping("/delivery")
	public String delivery() {
		return "home/delivery";
	}
	
}
