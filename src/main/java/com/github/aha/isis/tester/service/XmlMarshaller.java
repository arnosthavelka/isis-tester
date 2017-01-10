package com.github.aha.isis.tester.service;

import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.stereotype.Service;

@Service
public class XmlMarshaller {

    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(XmlMarshaller.class);

	@Autowired
	private ApplicationContext ctx;
	
    @Autowired
	private CastorMarshaller castor;
	
	@SuppressWarnings("unchecked")
	public <T> T loadXML(String fileName) {
        StreamSource source;
		try {
			source = readFileSource(fileName);
			return (T) this.castor.unmarshal(source);
		} catch (IOException e) {
			throw new RuntimeException("XML loading error!", e);
		}
		
	}

    /**
     * Read file content.
     * 
     * @param file file path
     * @return instance of <code>StreamSource</code>
     * @throws IOException when load fails
     */
    private StreamSource readFileSource(String xmlFile) throws IOException {
    	String filename = String.format("classpath:xml/%s", xmlFile.toLowerCase());
        LOG.debug("Loading XML ({})", filename);
        Resource resource = ctx.getResource(filename);
        return new StreamSource(resource.getInputStream());
    }
    
}
