package com.github.aha.isis.tester.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

	@Value("#{versionService.appVersion}")
	private String version;
	

	@GetMapping
	public ModelAndView list() {
		return new ModelAndView("views/home", "appVersion", version);
	}
	
}
