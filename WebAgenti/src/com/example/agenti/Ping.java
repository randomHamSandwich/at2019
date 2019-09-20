package com.example.agenti;

import java.io.Serializable;

import javax.ejb.Stateful;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.jms.JMSQueue;
import com.example.message.ACLMessage;
import com.example.message.Performative;

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
			System.out.println("Ping: zatrazen od strane:" + msg.getSender().getAgentType().getName() +" naziva: " +msg.getSender().getName());

			new JMSQueue(response);
			break;
			
		case INFORM:
			System.out.println("Ping: " +msg.getSender().getAgentType().getName() +" naziva: "+msg.getSender().getName()+" je odgovorio");

		default:
			System.out.println("Ping:"+ " dobio poruku od " + 
		msg.getSender().getAgentType().getName() +" naziva: "+msg.getSender().getName() + " tipa" + msg.getPerformative());
		}

	}


	
	

}
