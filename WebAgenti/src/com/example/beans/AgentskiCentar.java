package com.example.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.example.interfaces.IAgent;
import com.example.interfaces.IAgentskiCenter;

@Startup
@Singleton
@AccessTimeout(-1)
public class AgentskiCentar implements IAgentskiCenter {

	private List<Agent> agenti = new ArrayList<>();
	private String alias = "master";
	private String address;

	public List<Agent> getAgenti() {
		return agenti;
	}

	public void setAgenti(List<Agent> agenti) {
		this.agenti = agenti;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Agent findAgent(AID xxADI) {
		for (Agent aTemp : agenti) {
//			if (aTemp.getAID().equals(xxADI)) {
			if(aTemp.getAID().getAgentType().getName().equals(xxADI.getAgentType().getName()) && aTemp.getAID().getName().equals(xxADI.getName()) ) {
				return aTemp;
			}
		}
		return null;
	}

	@Override
	public void addAgent(Agent newAgent) {
		agenti.add(newAgent);
		System.out.println("agent kreiranj: " + newAgent.getAID() + " zzz " + newAgent );

	}

	@Override
	public boolean removeAgent(String type, String name) {
		for(Agent temp : agenti) {
			if(temp.getAID().getAgentType().getName().equals(type) && temp.getAID().getName().equals(name) ) {
				
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxx agent stoped: " + temp.getAID());
				agenti.remove(temp);
				return true;
			}
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxx no agent found with type_" + type+"_name_"+ name);
			return false;
		}
		
		
		return false;
	}

}
