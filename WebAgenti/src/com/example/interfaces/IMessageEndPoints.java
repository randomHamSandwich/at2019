package com.example.interfaces;

import javax.ejb.Remote;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.message.ACLMessage;
import com.example.message.Performative;


@Remote
public interface IMessageEndPoints {
	
//	 POST /messages � po�alji ACL poruku;
//	 � GET /messages � dobavi listu performativa
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMessage(ACLMessage message);
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Performative[] getPerformatives();

}
