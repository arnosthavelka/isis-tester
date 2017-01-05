package com.github.aha.isis.tester;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.github.aha.isis.tester.dto.IsisTestDTO;
import com.github.aha.isis.tester.dto.Question;
import com.github.aha.isis.tester.dto.Response;
import com.github.aha.isis.tester.dto.Section;
import com.github.aha.isis.tester.service.XmlMarshaller;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsisTestDTOTests {
	
    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(IsisTestDTOTests.class);
    
	private static final String FILE_NAME = "xml/3PA541_test.xml";
	
	@Autowired
	private XmlMarshaller xmlMarshaller;	
	
	@Test
	public void loadXML() throws IOException {
		long startTime = System.currentTimeMillis();
		IsisTestDTO dto = (IsisTestDTO) this.xmlMarshaller.loadXML(FILE_NAME);
        LOG.debug("comment={}, loading={}ms" , dto.getComment(), System.currentTimeMillis() - startTime);
        if (!CollectionUtils.isEmpty(dto.getSections())) {
        	LOG.debug("no. of params={}" , dto.getSections().size());
        	for (Section section : dto.getSections()) {
        		LOG.debug("Section [ident={}, title={}]", section.getIdent(), section.getTitle());
        		Collection<Question> questions = section.getQuestions();
        		if (!CollectionUtils.isEmpty(questions)) {
        			for (Question question : questions) {
        				LOG.debug("\tQuestion [ident={}, type={}, title={}]", question.getIdent(), question.getType(), question.getTitle());
                		Collection<Response> responses = question.getResponses();
                		if (!CollectionUtils.isEmpty(responses)) {
                			for (Response response : responses) {
                				LOG.debug("\t\tResponse [ident={}, right={}, title={}]", response.getIdent(), response.getRight(), response.getTitle());
                			}
                		}
        			}
        		}
			}
        }
	}
}
