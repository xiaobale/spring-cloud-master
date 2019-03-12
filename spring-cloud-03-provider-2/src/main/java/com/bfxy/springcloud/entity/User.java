package com.bfxy.springcloud.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -7290810180121451113L;

	private String id;
	
	private String name;
	
	public User() {
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
