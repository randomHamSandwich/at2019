package com.example.agenti;

import javax.ejb.Stateful;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.jms.JMSQueue;
import com.example.message.ACLMessage;
import com.example.message.Performative;
import com.sun.mail.handlers.message_rfc822;

@Stateful
public class Pong extends Agent {


	@Override
	public void handleMessage(ACLMessage msg) {
		// TODO Auto-generated method stub

		switch (msg.getPerformative()) {
		case REQUEST: 
			ACLMessage response = new ACLMessage();
			response.setPerformative(Performative.REQUEST);
			response.setSender(this.getAID());
			response.setReceavers(new AID[] {msg.getSender()});
			response.setConversationId(msg.getConversationId());
			
			System.out.println("11111111111111111111111111111111111111111111111111111111:");
			
			new JMSQueue(response);
			
			break;
		default:
			System.out.println("Ja Pong agent : " + this.getAID() + "  dobio poruku tipa: " + msg.getPerformative()
					+ "  od " + msg.getSender());
		}

	}

}
