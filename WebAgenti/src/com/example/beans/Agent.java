package com.example.beans;

import javax.ejb.Stateful;

import com.example.interfaces.IAgent;
import com.example.message.ACLMessage;

@Stateful
public class Agent implements IAgent {

	private static final long serialVersionUID = -6949183177665124181L;
	
	private AID myAID;

	public Agent(AID myAID) {
		super();
		this.myAID = myAID;
	}

	public Agent() {
	}

	
	@Override
	public void init(AID aid) {
	}

	@Override
	public void stop() {
	}

	@Override
	public void handleMessage(ACLMessage msg) {
	}

	@Override
	public AID getAID() {
		return myAID;
	}

	@Override
	public void setAID(AID aid) {
		this.myAID = aid;
	}

	@Override
	public String toString() {
		return "Agent [myAID=" + myAID + "]";
	}
	
	
}
