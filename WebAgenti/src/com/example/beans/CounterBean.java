package com.example.beans;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.example.interfaces.ICounter;

@Singleton
@Startup
public class CounterBean implements ICounter {

	private int counter; 
	
	@PostConstruct
	public void Init() {
		setCount(1220);
	}

	public void increment() {
		counter++;
	}
	
	public int getCount() {
		return counter+44;
	}

	public void setCount(int counter) {
		this.counter = counter;
	}	
}
