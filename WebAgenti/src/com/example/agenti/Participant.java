package com.example.agenti;

import java.io.Serializable;
import java.util.Random;

import javax.ejb.Stateful;
import javax.security.auth.login.FailedLoginException;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.jms.JMSQueue;
import com.example.message.ACLMessage;
import com.example.message.Performative;
@Stateful
public class Participant extends Agent implements Serializable {

	@Override
	public void handleMessage(ACLMessage msg) {
		switch (msg.getPerformative()) {
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
		
		Random rnd = new Random();
		int failureORNot = rnd.nextInt(101);
//		if(50>failureORNot ) {
		if(20>failureORNot ) {

			ACLMessage failureResponse= new ACLMessage();
			failureResponse.setPerformative(Performative.FAILURE);
			failureResponse.setSender(this.getAID());	
			failureResponse.setReceavers(new AID[] {msg.getSender()});
			
			System.out.println("Paticipant " + this.getAID().getName() + ": faliure " + failureORNot +"   needed >20 to not fail");
			new JMSQueue(failureResponse);
			return;
		}
		agentNotDone(msg, 90);
		
		ACLMessage informDoneREsponse= new ACLMessage();
		informDoneREsponse.setPerformative(Performative.INFORM_DONE);
		informDoneREsponse.setSender(this.getAID());
		informDoneREsponse.setReceavers(new AID[] {msg.getSender()});
		
//		System.out.println("Paticipant " + this.getAID().getName() + ": is DONE, inform-done");
		
		new JMSQueue(informDoneREsponse);
	}
	//agent is working on some actions 
	public void agentNotDone(ACLMessage msg, int boundry) {

		Random random= new Random();
		int a = random.nextInt(101);
		
		if(a <= boundry) {
			// we inform participant that agent is still working 
			ACLMessage informResult= new ACLMessage();
			informResult.setPerformative(Performative.INFORM_RESULT);
			informResult.setSender(this.getAID());	
			informResult.setReceavers(new AID[] {msg.getSender()});
			
			System.out.println("Paticipant " + this.getAID().getName() + ": is not done, Inform-result " + a+" , needed >" +boundry +" to finish working");
			
			new JMSQueue(informResult);
			agentNotDone(msg, boundry-5);
		}else {
			
			System.out.println("Paticipant " + this.getAID().getName() + ": is DONE, inform-done" + a +" , needed >" +boundry +" to finish working");
		}
		
		
	}

	private void handleRejectProposal(ACLMessage msg) {
		System.out.println("Participant " + this.getAID().getName() + ": Rejected by: "+ msg.getSender().getName() );

	}

	private void handleCallForProposal(ACLMessage msg) {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		int  proposedNumber= rnd.nextInt(101);
		if (proposedNumber <= 40) {
			//testing when all fail
//		if (proposedNumber <= 1111) {
			ACLMessage refuseResponse = new ACLMessage();
			refuseResponse.setPerformative(Performative.REFUSE);
			refuseResponse.setSender(this.getAID());
			refuseResponse.setReceavers(new AID[] { msg.getSender() });
			System.out.println("Participant " + this.getAID().getName() + ": Refused call for proposal " + proposedNumber+ ", needed >40 not to refuse");

			new JMSQueue(refuseResponse);
			return;
		}
		ACLMessage proposeResponse = new ACLMessage();
		proposeResponse.setPerformative(Performative.PROPOSE);
		proposeResponse.setSender(this.getAID());
		proposeResponse.setReceavers(new AID[] { msg.getSender() });
		proposeResponse.setContentObject(proposedNumber);
		
		System.out.println("Participant " + this.getAID().getName() + ": proposed:" + proposedNumber);
		new JMSQueue(proposeResponse);
		
	}

}
