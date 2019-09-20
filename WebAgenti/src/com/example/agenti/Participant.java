package com.example.agenti;

import java.io.Serializable;

import com.example.beans.Agent;
import com.example.message.ACLMessage;
import com.example.message.Performative;

public class Participant extends Agent implements Serializable {

	@Override
	public void handleMessage(ACLMessage msg) {
		switch(msg.getPerformative()) {
		case CALL_FOR_PROPOSAL:
			handleCallForProposal(msg);
			break;
		case REJECT_PROPOSAL:
			handleRejectProposal(msg);
			break;
		case ACCEPT_PROPOSAL:
			handleAcceptProposal(msg);
		break;
		}
	}

	private void handleAcceptProposal(ACLMessage msg) {
		// TODO Auto-generated method stub
		
	}

	private Performative ACCEPT_PROPOSAL(ACLMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	private void handleRejectProposal(ACLMessage msg) {
		// TODO Auto-generated method stub

	}

	private void handleCallForProposal(ACLMessage msg) {
		// TODO Auto-generated method stub

	}

}
