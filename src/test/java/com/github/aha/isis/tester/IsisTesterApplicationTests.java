package com.github.aha.isis.tester;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.github.aha.isis.tester.dto.Param;
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
        Param initParam = dto.getInitParam();
		if (initParam != null) {
        	LOG.debug("initParam [name={}, name={}]" , initParam.getName(), initParam.getValue());
        }
        if (!CollectionUtils.isEmpty(dto.getParams())) {
        	LOG.debug("no. of params={}" , dto.getParams().size());
        	for (Param param : dto.getParams()) {
        		LOG.debug("param [name={}, name={}]" , param.getName(), param.getValue());
			}
        }
	}
}
