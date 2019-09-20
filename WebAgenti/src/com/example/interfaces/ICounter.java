package com.example.interfaces;

import javax.ejb.Local;

@Local
public interface ICounter {

	void increment();
	int getCount();
}
