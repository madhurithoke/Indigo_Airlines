package com.user;

import com.model.Booking;
import com.model.Flight;
import com.service.BookingCounter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingCounter bookingCounter = new BookingCounter();
        Booking booking = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Step 1: Create a list of available flights
        List<Flight> availableFlights = new ArrayList<>();
        availableFlights.add(new Flight(101, "Delhi", "Banglore", LocalDateTime.parse("2024-09-30 10:00", formatter), 4000.0f));
        availableFlights.add(new Flight(102, "Pune", "Mumbai", LocalDateTime.parse("2024-10-01 15:00", formatter), 2000.0f));
        availableFlights.add(new Flight(103, "Banglore", "Pune", LocalDateTime.parse("2024-10-02 18:30", formatter), 5000.0f));
        availableFlights.add(new Flight(104, "Delhi", "Hydrabad", LocalDateTime.parse("2024-10-02 18:30", formatter), 6000.0f));
        while (true) {
            System.out.println("======== Indigo Airline Reservation System ==========");
            System.out.println("1. Show Available Flights");
            System.out.println("2. Book a Flight");
            System.out.println("3. Display Booking Details with Amount");
            System.out.println("4. Print Ticket");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Step 2: Display available flights in table format
                    System.out.println("===================================================================================");
                    System.out.printf("%-10s %-15s %-15s %-20s %-10s%n", "Flight ID", "From", "To", "Date and Time", "Price");
                    System.out.println("===================================================================================");
                    for (Flight flight : availableFlights) {
                        System.out.printf("%-10d %-15s %-15s %-20s $%-10.2f%n", 
                                          flight.getId(), flight.getFrom(), flight.getTo(), flight.getDt(), flight.getTicketPrice());
                    }
                    System.out.println("===================================================================================");
                    break;

                case 2:
                    // Step 3: Book a flight
                    System.out.print("Enter the Flight ID to book: ");
                    int flightId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Find the selected flight from the available flights list
                    Flight selectedFlight = null;
                    for (Flight flight : availableFlights) {
                        if (flight.getId() == flightId) {
                            selectedFlight = flight;
                            break;
                        }
                    }

                    if (selectedFlight == null) {
                        System.out.println("Invalid Flight ID. Please try again.");
                        break;
                    }

                    // Proceed with booking
                    System.out.print("Enter number of seats to book: ");
                    int noOfSeats = scanner.nextInt();

                    // Book the flight
                    booking = bookingCounter.bookFlight(selectedFlight, noOfSeats);
                    System.out.println("Flight booked successfully!");
                    break;

                case 3:
                    // Display booking details with amount
                    if (booking != null) {
                        System.out.println("=====================================================================");
                        System.out.println("Booking Details:");
                        System.out.println("Flight From: " + booking.getFlight().getFrom());
                        System.out.println("Flight To: " + booking.getFlight().getTo());
                        System.out.println("Number of Seats: " + booking.getNoOfSeats());
                        System.out.println("Total Amount: $" + booking.getBookingAmt());
                        System.out.println("=====================================================================");
                    } else {
                        System.out.println("No bookings found. Please book a flight first.");
                    }
                    break;

                case 4:
                    // Print the ticket in table format and save it to a.txt file
                    if (booking != null) {
                        System.out.println("============== Flight Ticket ==============");
                        System.out.printf("%-15s: %s%n", "Flight ID", booking.getFlight().getId());
                        System.out.printf("%-15s: %s%n", "From", booking.getFlight().getFrom());
                        System.out.printf("%-15s: %s%n", "To", booking.getFlight().getTo());
                        System.out.printf("%-15s: %s%n", "Date and Time", booking.getFlight().getDt());
                        System.out.printf("%-15s: %d%n", "Seats Booked", booking.getNoOfSeats());
                        System.out.printf("%-15s: $%.2f%n", "Total Amount", booking.getBookingAmt());
                        System.out.println("=====================================================================");

                        // Step 4: Write the ticket to a .txt file
                        writeTicketToFile(booking);
                    } else {
                        System.out.println("No bookings found. Please book a flight first.");
                    }
                    break;

                case 5:
                    // Exit the system with a thank you message
                    System.out.println("Thank you for choosing Indigo Airlines. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Step 5: Method to write the ticket to a .txt file
    public static void writeTicketToFile(Booking booking) {
        String fileName = "tickets/booking_" + booking.getBookingId() + ".txt";
        File file = new File("tickets");
        
        // Create the "tickets" directory if it doesn't exist
        if (!file.exists()) {
            file.mkdirs();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("============== Flight Ticket ==============\n");
            writer.write(String.format("%-15s: %s\n", "Flight ID", booking.getFlight().getId()));
            writer.write(String.format("%-15s: %s\n", "From", booking.getFlight().getFrom()));
            writer.write(String.format("%-15s: %s\n", "To", booking.getFlight().getTo()));
            writer.write(String.format("%-15s: %s\n", "Date and Time", booking.getFlight().getDt()));
            writer.write(String.format("%-15s: %d\n", "Seats Booked", booking.getNoOfSeats()));
            writer.write(String.format("%-15s: $%.2f\n", "Total Amount", booking.getBookingAmt()));
            writer.write("==============================================================================");
            System.out.println("Ticket saved successfully in file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while saving the ticket: " + e.getMessage());
        }
    }
}
