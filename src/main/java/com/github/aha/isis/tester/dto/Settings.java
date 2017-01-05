package com.github.aha.isis.tester.dto;

import java.io.Serializable;

public class Settings implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean fooEnabled;

    public boolean isFooEnabled() {
        return fooEnabled;
    }

    public void setFooEnabled(boolean fooEnabled) {
        this.fooEnabled = fooEnabled;
    }

}
