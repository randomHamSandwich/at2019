package com.example.interfaces;

import java.io.Serializable;

import com.example.beans.AID;
import com.example.message.ACLMessage;

public interface IAgent  extends Serializable{
	
	public void init(AID aid);

	public void stop();
	
	public void handleMessage(ACLMessage msg);

	public void setAID(AID aid);
	
	public AID getAID();
}
