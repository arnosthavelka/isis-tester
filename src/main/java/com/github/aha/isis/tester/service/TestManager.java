package com.github.aha.isis.tester.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ConfigurableObjectInputStream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.github.aha.isis.tester.dto.IsisTestDTO;

@Service
public class TestManager {
	
    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(TestManager.class);

    @Value("${data.xml.path}")
    private String xmlPath;
    
    @Autowired
    ResourceLoader resourceloader;
    
    @Autowired
	private XmlMarshaller xmlMarshaller;	
	
	@Cacheable("menu")
	public Collection<String> getTests() {
		LOG.debug("Loading available XML tests ...");
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
		// add file extension to match XML file
		Object dtoObject = this.xmlMarshaller.loadXML(String.format("%s/%s.xml", xmlPath, testID));
		// necessary to do additional serialization/deserialization due to
		// java.lang.ClassCastException: com.github.aha.isis.tester.dto.IsisTestDTO cannot be cast to com.github.aha.isis.tester.dto.IsisTestDTO
		// caused by usage of 2 classloader by devops library 
		byte[] data = serializeDTO(dtoObject);
		IsisTestDTO dto = deserializeDTO(data);
		return dto;
	}

	private IsisTestDTO deserializeDTO(byte[] data) {
		IsisTestDTO dto = null;
		try {
			ByteArrayInputStream fileIn = new ByteArrayInputStream(data);
			ObjectInputStream in = new ConfigurableObjectInputStream(fileIn,
					Thread.currentThread().getContextClassLoader());
			dto = (IsisTestDTO) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			String msg = "Deserialization of marshalled XML failed!";
			LOG.error(msg, e);
			throw new RuntimeException(msg, e);
		}
		return dto;
	}
	
	private byte[] serializeDTO(Object dtoObject) {
		byte[] result = null;
		try {
			ByteArrayOutputStream data = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(data);
			out.writeObject(dtoObject);
			out.close();
			result = data.toByteArray();
			data.close();
		} catch (IOException e) {
			String msg = "Serialization of marshalled XML failed!";
			LOG.error(msg, e);
			throw new RuntimeException(msg, e);
		}

		return result;
	}

}
