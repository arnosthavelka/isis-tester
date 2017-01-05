package com.github.aha.isis.tester.dto;

import java.io.Serializable;
import java.util.Collection;

public class IsisTestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String comment;
	
	private Collection<Section> sections;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Collection<Section> getSections() {
		return sections;
	}

	public void setSections(Collection<Section> sections) {
		this.sections = sections;
	}

}
