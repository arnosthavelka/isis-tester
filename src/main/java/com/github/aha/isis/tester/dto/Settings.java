package com.github.aha.isis.tester.dto;

import java.io.Serializable;
import java.util.Collection;

public class Settings implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean fooEnabled;
	
	private Param initParam;
	
	private Collection<Param> params;

    public boolean isFooEnabled() {
        return fooEnabled;
    }

    public void setFooEnabled(boolean fooEnabled) {
        this.fooEnabled = fooEnabled;
    }

	public Collection<Param> getParams() {
		return params;
	}

	public void setParams(Collection<Param> params) {
		this.params = params;
	}

	public Param getInitParam() {
		return initParam;
	}

	public void setInitParam(Param initParam) {
		this.initParam = initParam;
	}

}
