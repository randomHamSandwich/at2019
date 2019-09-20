package com.example.interfaces;

import java.util.List;

import com.example.beans.AID;
import com.example.beans.Agent;

public interface IAgentskiCenter {

	Agent findAgent(AID xxADI);

	void addAgent(Agent newAgent);
	
	public List<Agent> getAgenti();
	
	public boolean removeAgent(String type, String name);

}
