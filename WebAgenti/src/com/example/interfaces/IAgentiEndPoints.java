package com.example.interfaces;

import javax.ejb.Remote;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.beans.AID;

@Remote
public interface IAgentiEndPoints {
	

	
//GET /agents/classes - dobavi listu svih agenata na sistemu;
//GET /agents/running - dobavi sve pokrenute agente sa sistema;
//PUT /agents/running/{type}/{name} - pokreni agenta odredjenog tipa sa zadatim imenom;
//DELETE /agents/running/{aid} -zaustavi odredjenog agenta;
	@GET
	@Path("classes")
	public Response getClasses();
	
	@GET
	@Path("/running")
	public Response getRunningAgents();
	
	
	@POST
	@Path("/running/{type}/{name}")
	public Response startAgents(@PathParam("type") String type, @PathParam("name") String name);
	
	
	@DELETE
	@Path("running/{type}/{name}")
	public Response deleteAgent(@PathParam("type") String type, @PathParam("name") String name);
	
	
}
