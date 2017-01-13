package com.github.aha.isis.tester.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.aha.isis.tester.service.TestManager;

@Controller
@RequestMapping("/")
public class HomeController {

	@Value("#{versionService.appVersion}")
	private String version;
	
	@Autowired
	private TestManager manager;
	
	@GetMapping
	public ModelAndView list() {
		
		Map<String, Object> params = new HashMap<>();
		params.put("menuItems", manager.getTests());
		params.put("appVersion", version);
		
		return new ModelAndView("views/home", params);
	}
	
}
