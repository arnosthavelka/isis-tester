package com.github.aha.isis.tester.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.aha.isis.tester.dto.IsisTestDTO;

@Service
public class TestManager {
	
	@Autowired
	private XmlMarshaller xmlMarshaller;	
	
	@Cacheable("tests")
	public IsisTestDTO loadData(String testID) {
		String filename = String.format("xml/%s_test.xml", testID.toLowerCase());
		IsisTestDTO dto = (IsisTestDTO) this.xmlMarshaller.loadXML(filename);
		return dto;
	}

}
