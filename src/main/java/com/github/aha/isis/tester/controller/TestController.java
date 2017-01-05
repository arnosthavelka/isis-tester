package com.github.aha.isis.tester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {


	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") String test) {
		return new ModelAndView("views/test", "test", test);
	}
	
}
