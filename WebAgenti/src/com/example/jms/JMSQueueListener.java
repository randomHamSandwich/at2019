package com.example.jms;

import javax.ejb.AccessTimeout;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.interfaces.IAgentskiCenter;
import com.example.message.ACLMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/mojQueue") })
@AccessTimeout(-1)
public class JMSQueueListener implements MessageListener {

	@EJB
	IAgentskiCenter center;

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub

		ObjectMessage objectMessage = (ObjectMessage) arg0;

		ACLMessage message;
		try {
			message = (ACLMessage) objectMessage.getObject();

			if (message.getReceavers() == null || message.getReceavers().length == 0) {
				System.out.println("nema recevera");
				return;
			}

			// za sve resevere
			for (AID xxADI : message.getReceavers()) {

				try {

					Agent a = center.findAgent(xxADI);

					if (a == null) {
						System.out.println("agenet je null xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					} else {
						
						a.handleMessage(message);	
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
