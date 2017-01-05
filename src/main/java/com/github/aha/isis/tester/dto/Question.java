package com.github.aha.isis.tester.dto;

import java.io.Serializable;
import java.util.Collection;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ident;
	
	/**
	 * Values:
	 * <ul>
	 * <li><strong>1ofN<strong> - radio box</li>
	 * <li><strong>MofN<strong> - check box</li>
	 * <li><strong>FIB<strong> - input box</li>
	 * </ul>
	 */
	private String type;
	
	private String title;
	
	private String comment;
	
	private Integer txtScore;
	
	private Collection<Response> responses;

	public Integer getIdent() {
		return ident;
	}

	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getTxtScore() {
		return txtScore;
	}

	public void setTxtScore(Integer txtscore) {
		this.txtScore = txtscore;
	}

	public Collection<Response> getResponses() {
		return responses;
	}

	public void setResponses(Collection<Response> responses) {
		this.responses = responses;
	}
	
}
