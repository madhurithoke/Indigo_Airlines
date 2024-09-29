package com.service;

import com.model.Flight;
import com.model.Booking;
public interface Indigo {
	
 Booking bookFlight(Flight f,int noOfSeats);
 void getStatus(Booking b);
}
