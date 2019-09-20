package com.example.agenti;

import java.io.Serializable;

import javax.ejb.Stateful;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.message.ACLMessage;
import com.example.message.Performative;
import com.examplejms.JMSQueue;

@Stateful
public class Ping extends Agent implements Serializable {


	@Override
	public void handleMessage(ACLMessage msg) {
		// TODO Auto-generated method stub

		switch (msg.getPerformative()) {

		case REQUEST:
			ACLMessage response = new ACLMessage();
			response.setPerformative(Performative.REQUEST);
			response.setSender(this.getAID());
			response.setReceavers(new AID[] { this.getAID() });
			response.setConversationId(msg.getConversationId());
			System.out.println("11111111111111111111111111111111111111111111111111111111:");

			new JMSQueue(response);
			break;

		default:
			System.out.println("333333333333333333333333333333333333");
		}

	}


	
	

}
