package com.jdjhaha.vocab.login.jwt;

import java.io.Serializable;

public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
    private String cco_c;
    private String id;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest() {   }

    public JwtRequest(String cco_c, String id, String password) {
    	this.setCco_c(cco_c);
    	this.setId(id);
        this.setPassword(password);
    }

    public String getCco_c() {
		return cco_c;
	}

	public void setCco_c(String cco_c) {
		this.cco_c = cco_c;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
