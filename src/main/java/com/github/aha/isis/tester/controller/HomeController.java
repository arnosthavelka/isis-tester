package com.github.aha.isis.tester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController extends AbstractController {

	@GetMapping
	public ModelAndView list() {
		return new ModelAndView("views/home", prepareParams("menuItems", manager.getTests()));
	}
	
}
