package com.example.message;

import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.interfaces.IAgentskiCenter;
import com.example.interfaces.IMessageEndPoints;
import com.example.jms.JMSQueue;


@Path("/messages")
@LocalBean
@Stateless
@AccessTimeout(-1)
public class MessageEndPoints implements IMessageEndPoints{
	
	@EJB
	IAgentskiCenter centar;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMessage(ACLMessage message)
	{
		new JMSQueue(message);
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Performative[] getPerformatives() {
		
		return Performative.values();
	}
	
	

}
