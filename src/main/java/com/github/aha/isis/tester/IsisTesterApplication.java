package com.github.aha.isis.tester;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableCaching
public class IsisTesterApplication extends WebMvcConfigurerAdapter {

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

	// http://www.fileformat.info/info/unicode/char/00fd/index.htm
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.forLanguageTag("cs"));
        return slr;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
    }
}
