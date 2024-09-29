package com.model;
import com.model.Flight;
import java.time.LocalDateTime;
public class Booking {

	 private int bookingId;
	    private LocalDateTime bookingTime;
	    private int noOfSeats;
	    private Flight flight;
	    private float bookingAmt;
	    
	    public Booking(int bookingId, LocalDateTime bookingTime, int noOfSeats, Flight flight, float bookingAmt) {
	        this.bookingId = bookingId;
	        this.bookingTime = bookingTime;
	        this.noOfSeats = noOfSeats;
	        this.flight = flight;
	        this.bookingAmt = bookingAmt;
	    }
		public int getBookingId() {
			return bookingId;
		}
		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
		}
		public LocalDateTime getBookingTime() {
			return bookingTime;
		}
		public void setBookingTime(LocalDateTime bookingTime) {
			this.bookingTime = bookingTime;
		}
		public int getNoOfSeats() {
			return noOfSeats;
		}
		public void setNoOfSeats(int noOfSeats) {
			this.noOfSeats = noOfSeats;
		}
		public Flight getFlight() {
			return flight;
		}
		public void setFlight(Flight flight) {
			this.flight = flight;
		}
		public float getBookingAmt() {
			return bookingAmt;
		}
		public void setBookingAmt(float bookingAmt) {
			this.bookingAmt = bookingAmt;
		}
	

}
