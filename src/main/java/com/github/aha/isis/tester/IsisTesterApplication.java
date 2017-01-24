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
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

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
    	// we can use SessionLocaleResolver as well
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("cs"));
        return resolver;
    }
    
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// we can use the default name ("locale") as well
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
    }
}