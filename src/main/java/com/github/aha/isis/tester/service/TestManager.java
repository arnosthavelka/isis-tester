package com.github.aha.isis.tester.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.github.aha.isis.tester.dto.IsisTestDTO;

@Service
public class TestManager {
	
    @Value("${data.xml.path}")
    private String xmlPath;
    
    @Autowired
    ResourceLoader resourceloader;
    
    @Autowired
	private XmlMarshaller xmlMarshaller;	
	
//	@Cacheable("menu")
	public Collection<String> getTests() {
		Collection<String> items = new ArrayList<>();
		
		Resource[] resources;
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
			resources = resolver.getResources(xmlPath + "/*.xml");
			for (Resource resource: resources){
				String filename = resource.getFilename();
				// get just name (not file extension)
				items.add(filename.substring(0, filename.indexOf(".")));
			}		
		} catch (IOException e) {
			throw new RuntimeException("Retrieving XML files error", e);
		}
		return items;
	}

	@Cacheable("tests")
	public IsisTestDTO loadData(String testID) {
		// add file extension to match XML file4
		IsisTestDTO dto = (IsisTestDTO) this.xmlMarshaller.loadXML(String.format("%s.xml", testID));
		return dto;
	}

}
