package com.example.agenti;

import javax.ejb.Stateful;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.jms.JMSQueue;
import com.example.message.ACLMessage;
import com.example.message.MyLogger;
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
			response.setPerformative(Performative.INFORM);
			response.setSender(this.getAID());
			response.setReceavers(new AID[] {msg.getSender()});
			
			System.out.println("Pong: zatrazen od strane:" + msg.getSender().getAgentType().getName() +" naziva: " +msg.getSender().getName());
			MyLogger.log("Pong: zatrazen od strane:" + msg.getSender().getAgentType().getName() +" naziva: " +msg.getSender().getName());
			
			new JMSQueue(response);
			
			break;
		default:
			System.out.println("Ping:"+ " dobio poruku od " + 
		msg.getSender().getAgentType().getName() +" naziva: "+msg.getSender().getName() + " tipa" + msg.getPerformative());
			
			MyLogger.log("Ping:"+ " dobio poruku od " + 
					msg.getSender().getAgentType().getName() +" naziva: "+msg.getSender().getName() + " tipa" + msg.getPerformative());
		}

	}

}
