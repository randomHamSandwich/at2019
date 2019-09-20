package com.example.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.example.interfaces.ICounter;



@Stateless
@Path("/counter")
public class ExampleController {

	@EJB
	ICounter counter;
	
	@GET()
	public int Get() {
		counter.increment();
		counter.increment();
		counter.increment();
		counter.increment();
		counter.increment();
		counter.increment();
		return counter.getCount();
	}
}
