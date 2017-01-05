package com.github.aha.isis.tester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.castor.CastorMarshaller;

@SpringBootApplication
public class IsisTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsisTesterApplication.class, args);
	}

	@Bean
	public CastorMarshaller castor() {
		CastorMarshaller castor = new CastorMarshaller();
		castor.setMappingLocation(new ClassPathResource("mapping.xml"));
		castor.setIgnoreExtraElements(true);
		castor.setIgnoreExtraAttributes(true);
		return castor;
	}
}
