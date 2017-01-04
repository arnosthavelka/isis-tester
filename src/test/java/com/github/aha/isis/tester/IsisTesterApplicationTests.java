package com.github.aha.isis.tester;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.aha.isis.tester.dto.Settings;
import com.github.aha.isis.tester.service.XmlMarshaller;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsisTesterApplicationTests {
	
    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(IsisTesterApplicationTests.class);
    
	private static final String FILE_NAME = "settings.xml";
	
	@Autowired
	private XmlMarshaller xmlMarshaller;	
	
	@Test
	public void loadXML() throws IOException {
        Settings dto = (Settings) this.xmlMarshaller.loadXML(FILE_NAME);
        LOG.debug("fooEnabled={}" , dto.isFooEnabled());
	}
}
