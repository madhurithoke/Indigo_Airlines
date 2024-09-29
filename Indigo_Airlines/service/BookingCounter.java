package com.service;

import com.model.Booking;
import com.model.Flight;

import java.time.LocalDateTime;

public class BookingCounter implements Indigo {

    @Override
    public Booking bookFlight(Flight flight, int noOfSeats) {
        // Validate flight and number of seats
        if (flight == null || noOfSeats <= 0) {
            throw new IllegalArgumentException("Invalid flight or number of seats.");
        }

        // Calculate the total booking amount
        float totalAmount = flight.getTicketPrice() * noOfSeats;

        // Create a new booking object
        Booking booking = new Booking(1, LocalDateTime.now(), noOfSeats, flight, totalAmount);
        
        // Print success message
        System.out.println("Flight booked successfully!");

        // Return the booking object
        return booking;
    }

    @Override
    public void getStatus(Booking booking) {
        // Validate booking
        if (booking == null) {
            throw new IllegalArgumentException("Invalid booking.");
        }

        // Print the booking status
        System.out.println("Booking Status:");
        System.out.println(booking);
    }
}
