package com.github.aha.isis.tester.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.github.aha.isis.tester.service.TestManager;

public abstract class AbstractController {

	@Value("#{versionService.appVersion}")
	private String version;
	
	@Autowired
	protected TestManager manager;
	
	protected <T> Map<String, Object> prepareParams(String key, T value) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("menuItems", manager.getTests());
		params.put("appVersion", version);
		if (!StringUtils.isEmpty(key)) {
			params.put(key, value);
		}
		
		return params;
	}
	
}
