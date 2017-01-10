package com.github.aha.isis.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.castor.CastorMarshaller;

@SpringBootApplication
@EnableCaching
public class IsisTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsisTesterApplication.class, args);
	}

    @Autowired
    ResourceLoader resourceloader;
    
	@Bean
	public CastorMarshaller castor() {
		CastorMarshaller castor = new CastorMarshaller();
		castor.setMappingLocation(resourceloader.getResource("classpath:mapping.xml"));
		castor.setIgnoreExtraElements(true);
		castor.setIgnoreExtraAttributes(true);
		return castor;
	}

}
