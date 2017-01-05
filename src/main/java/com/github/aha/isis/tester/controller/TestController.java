package com.github.aha.isis.tester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.aha.isis.tester.dto.IsisTestDTO;
import com.github.aha.isis.tester.service.TestManager;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestManager manager;
	
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") String test) {
		IsisTestDTO dto = manager.loadData(test);
		dto.setId(test);
		
		return new ModelAndView("views/test", "dto", dto);
	}
	
}
