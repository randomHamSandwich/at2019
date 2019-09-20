package com.example.beans;

import java.io.Serializable;

public class AgentType implements Serializable{

	String name;
	String module;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public AgentType(String name, String module) {
		super();
		this.name = name;
		this.module = module;
	}

	public AgentType() {
	}

	@Override
	public String toString() {
		return "AgentType [name=" + name + ", module=" + module + "]";
	}
	
	

}
