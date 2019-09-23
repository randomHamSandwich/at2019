package com.example.rest;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.beans.AID;
import com.example.beans.Agent;
import com.example.beans.AgentType;
import com.example.beans.AgentskiCentar;
import com.example.interfaces.IAgent;
import com.example.interfaces.IAgentiEndPoints;
import com.example.interfaces.IAgentskiCenter;
import com.example.interfaces.ICounter;

@Path("/agents")
@LocalBean
@Stateless
@AccessTimeout(-1)
public class AgentsEndPoints implements IAgentiEndPoints {

	@EJB
	IAgentskiCenter centar;

	@GET()
	@Path("/zzz")
	public int zzz() {
		System.out.println("test");
		return 666;
	}

	@POST
	@Path("/pTest")
	public int pTest() {
		System.out.println("test");
		return 777;
	}
	//--------------------------------------------------------------------
	
	
	
	@GET
	@Path("classes")
	public Response getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/addAgent")
//	public Response addAgent(AID aid) {
//		centar.addAgent(new Agent(aid));
//		return Response.status(200).build();
//	}
	
	@GET
	@Path("/running")
	public Response getRunningAgents() {
		// TODO Auto-generated method stub
		
		String sTemp = "";
		for(Agent a : centar.getAgenti()) {
			sTemp+=", "+a.getAID().toString();
		}
		
		  ResponseBuilder builder=Response.ok("stiglo " + sTemp);
		
		  return builder.build();
	}
	
	

	@Override
	@POST
	@Path("/running/{type}/{name}")
	public Response startAgents(@PathParam("type") String type, @PathParam("name") String name) {
		// TODO Auto-generated method stub
		try {
			Context context = new InitialContext();
			IAgent iAgent = (IAgent) context.lookup("java:module/" + type);
			
			AID aid = new AID(name, new AgentType(type, "agent"));
			
			// da li ima agent sa istim imenom istog tipa
			for(Agent aTemp : centar.getAgenti()) {
				if(aTemp.getAID().getAgentType().getName().equals(aid.getAgentType().getName())
						&& aTemp.getAID().getName().equals(aid.getName()) ) {
					System.out.println("bbbbbbbbbb agent vec postoji"  );
					return Response.status(400).build();
				}
			}

			Agent agent = (Agent) iAgent;
			agent.setAID(aid);
			
			centar.addAgent(agent);


		} catch (Exception e) {
			System.out.println("error in running eya xxxxxxxx");
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}



	@DELETE
	@Path("running/{type}/{name}")
	public Response deleteAgent(@PathParam("type") String type, @PathParam("name") String name) {
		// TODO Auto-generated method stub

		if(centar.removeAgent(type, name)) {
			return Response.ok().build();
		}
		else {
			return Response.status(400).build();
		}
			
		
	}





}
