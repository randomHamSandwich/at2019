package com.example.agenti;

import java.io.Serializable;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.example.beans.Agent;
import com.example.message.ACLMessage;
import com.example.message.Performative;

public class Initiator extends Agent implements Serializable {

	@Override
	public void handleMessage(ACLMessage msg) {

		switch (msg.getPerformative()) {
		case REQUEST:
			// salji call for proposal
			handleRequest(msg);
			break;
		case REFUSE:
			handleRefuse(msg);
			break;
		case PROPOSE:
			handlePropose(msg);
			break;
		case FAILURE:
			handleFailure(msg);
			break;
		case INFORM_DONE:
			handleInformDone(msg);
			break;
		case INFORM_RESULT:
			handleInformResult(msg);
			break;
		}

	}



	private void handleInformResult(ACLMessage msg) {
		// TODO Auto-generated method stub
		
	}



	private void handleInformDone(ACLMessage msg) {
		// TODO Auto-generated method stub
		
	}



	private void handleFailure(ACLMessage msg) {
		// TODO Auto-generated method stub
		
	}



	private void handleRefuse(ACLMessage msg) {
		// TODO Auto-generated method stub
		
	}

	private void handleRequest(ACLMessage msg) {
		// TODO Auto-generated method stub
		
	}

	private void handlePropose(ACLMessage msg) {
		// TODO Auto-generated method stub
		
	}
}
