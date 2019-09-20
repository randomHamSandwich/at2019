package com.example.beans;

import java.io.Serializable;

import com.example.interfaces.IAgentskiCenter;

//AID (Agent id) sadrzi sledeca polja: name, host (Agentski centar) i type (Agent type)
// ne treba agenski centra sve je na jednom mestu, nemam cvorove..
public class AID implements Serializable {

	private String name;
//	private IAgentskiCenter host;
	private AgentType agentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AgentType getAgentType() {
		return agentType;
	}

	public void setAgentType(AgentType agentType) {
		this.agentType = agentType;
	}

	public AID(String name, AgentType agentType) {
		super();
		this.name = name;
		this.agentType = agentType;
	}

	@Override
	public String toString() {
		return "AID [name=" + name + ", agentType=" + agentType + "]";
	}

	public AID() {
		// TODO Auto-generated constructor stub
	}

}
