package com.example.agenti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.jms.JMSQueue;
import com.example.message.ACLMessage;
import com.example.message.Performative;

@Stateful
public class Initiator extends Agent implements Serializable {

	private HashMap<String, Integer> proposalMap = new HashMap<String, Integer>();
	private AID[] allParticipants;

	@Override
	public void handleMessage(ACLMessage msg) {

		switch (msg.getPerformative()) {
		case REQUEST:
			// salji call for proposal
			handleRequest(msg);
			wait(msg);
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
		// handle resume izaberemo najbolju ponudu
		case RESUME:
			if(proposalMap.isEmpty()) {
				System.out.println("Initiator " + this.getAID().getName() + ": no Participant made proposal");
				break;
			}
			
			if (msg.getSender().getName().equals(this.getAID().getName())) {
				handleResume(msg);
			} else {
				System.out.println("Initiator " + this.getAID().getName() + ": dobio resume al ne od samog sebe");
			}

			break;
		}

	}

// ceka pa poslaji sam sebi poruku i nastavi rad sa predpostavkom da su agenti zavrshili slanje refuse ili propose
//bolje da ovako imamo cekanje pa da radimo nego neki counter svih participanata jer neki od njih moze da pukne
	private void wait(ACLMessage msg) {

		System.out.println("Initiator " + this.getAID().getName() + ": is waiting for participants ");
		ACLMessage response = new ACLMessage();
		response.setPerformative(Performative.RESUME);
		response.setSender(this.getAID());
		response.setReceavers(new AID[] { this.getAID() });
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
					Thread.sleep(4000);
					System.out.println("ccccccccccccccccccccccccccccccccccccccccccccc");
					System.out.println(" izashao iz sleep");
					new JMSQueue(response);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
//		new JMSQueue(response	);
	}

	//
	private void handleResume(ACLMessage msg) {
		Map.Entry<String, Integer> maxTemp = null;
		for (Map.Entry<String, Integer> ent : proposalMap.entrySet()) {
			if (maxTemp == null || ent.getValue().compareTo(maxTemp.getValue()) >= 0) {
				maxTemp = ent;
			}
//ne ne ne eeee ne ne ne ne nemoj to da radish........
//			proposalMap.remove(ent.getKey(), ent.getValue());
		}
		for (int i = 0; i < allParticipants.length; i++) {
			if (allParticipants[i].getName().equals(maxTemp.getKey())) {
				ACLMessage acceptResponse = new ACLMessage();
				acceptResponse.setPerformative(Performative.ACCEPT_PROPOSAL);
				acceptResponse.setSender(this.getAID());
				acceptResponse.setReceavers(new AID[] { allParticipants[i] });
				acceptResponse.setContentObject(maxTemp.getValue());

				new JMSQueue(acceptResponse);

			}

			else {
				// reject-proposal
				ACLMessage rejectProposalResponse = new ACLMessage();
				rejectProposalResponse.setPerformative(Performative.REJECT_PROPOSAL);
				rejectProposalResponse.setSender(this.getAID());
				rejectProposalResponse.setReceavers(new AID[] { allParticipants[i] });

				new JMSQueue(rejectProposalResponse);
			}
		}

	}

	private void handleInformResult(ACLMessage msg) {
		System.out.println("Initiator " + this.getAID().getName() + ": Participant " + msg.getSender().getName()
				+ " is working, not jet done " + msg.getPerformative());
	}

	private void handleInformDone(ACLMessage msg) {
		System.out.println("Initiator " + this.getAID().getName() + ": Participant " + msg.getSender().getName()
				+ " is DONE " + msg.getPerformative());

	}

	private void handleFailure(ACLMessage msg) {
		System.out.println("Initiator " + this.getAID().getName() + ": Participant " + msg.getSender().getName()
				+ " has failed " + msg.getPerformative());

	}

	private void handleRefuse(ACLMessage msg) {
		System.out.println("Initiator " + this.getAID().getName() + ": Participant " + msg.getSender().getName()
				+ " refused call for proposal");

	}

	private void handleRequest(ACLMessage msg) {
		if (msg.getReceavers().length == 0) {
			System.out.println(" Nema participants prekinuto pppppppppppppppppppppppp");
			return;
		}

		ACLMessage response = new ACLMessage();
		response.setPerformative(Performative.CALL_FOR_PROPOSAL);
		response.setSender(this.getAID());

		response.setReceavers(msg.getReceaversFromInitiator());
		allParticipants = msg.getReceaversFromInitiator();

		System.out.println("Initiator " + this.getAID().getName() + ": sends call for proposal");
		new JMSQueue(response);

	}

//cuvamo ime agenta koje je jedinstveno, i vrednost sto je ponudio
	private void handlePropose(ACLMessage msg) {
		int temp = (int) msg.getContentObject();
		proposalMap.put(msg.getSender().getName(), temp);

	}
}
