package com.github.aha.isis.tester.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.aha.isis.tester.dto.IsisTestDTO;

@Service
public class TestManager {
	
	@Autowired
	private XmlMarshaller xmlMarshaller;	
	
//	@Cacheable("menu")
	public Collection<String> getTests() {
		Collection<String> items = new ArrayList<>();
		items.add("3PA523");
		items.add("3PA541");
		items.add("3PA542");
		items.add("3PA546");
		return items;
	}

	@Cacheable("tests")
	public IsisTestDTO loadData(String testID) {
		IsisTestDTO dto = (IsisTestDTO) this.xmlMarshaller.loadXML(String.format("%s_test.xml", testID));
		return dto;
	}

}
