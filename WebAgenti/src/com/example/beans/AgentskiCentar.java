package com.example.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.ejb.Lock;
import javax.ejb.LockType;
import com.example.interfaces.IAgent;
import com.example.interfaces.IAgentskiCenter;

@Startup
@Singleton
@Lock(LockType.READ)
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
	@Lock(LockType.WRITE)
	@Override
	public void addAgent(Agent newAgent) {
		agenti.add(newAgent);
		System.out.println("agent kreiranj: " + newAgent.getAID() + " zzz " + newAgent );

	}
	@Lock(LockType.WRITE)
	@Override
	public boolean removeAgent(String type, String name) {
		for(Agent temp : agenti) {
			if(temp.getAID().getAgentType().getName().equals(type) && temp.getAID().getName().equals(name) ) {
				
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxx agent stoped: " + temp.getAID());
				agenti.remove(temp);
				return true;
			}
			System.out.println("----------------------------------------------------");
			for(Agent a : agenti) {
				System.out.println(a.getAID().toString());
			}
			System.out.println("----------------------------------------------------");
			System.out.println("ooooooooooooooooooooooooooooo NO agent found with type:" + type+" name:"+ name);
			return false;
		}
		
		
		return false;
	}

	@Override
	public ArrayList<AgentType> getAgentTypes() {
		ArrayList<AgentType> retVal = new ArrayList<AgentType>();
		
		try
		{
			InitialContext ctx = new InitialContext();
			NamingEnumeration<NameClassPair> list = ctx.list("java:module");
			
			while (list.hasMore()) 
			{
				String sTemp = list.next().getName();
				
				if (sTemp.contains("!com.example.agenti."))
				{
					retVal.add(new AgentType(sTemp.split("!")[0], "agent"));
					System.out.println("pronadjen agent tipa: " + sTemp.split("!")[0]);
				}
					
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return retVal;
	}

}
