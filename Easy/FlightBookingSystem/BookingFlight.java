package BookingSystems.FlightBookingSystem;

import java.util.*;

public class BookingFlight {
    static int id = 0;
    int flightId;
    int tickets;
    int price;
    List<String> passengerDetails;
    List<Integer> passengerCost;
    List<Integer> bookedTickets;
    List<Integer> passengerIds;

    public BookingFlight() {
        tickets = 100;
        price = 5000;
        id = id + 1;
        flightId = id;
        passengerDetails = new ArrayList<>();
        passengerIds = new ArrayList<>();
        bookedTickets = new ArrayList<>();
        passengerCost = new ArrayList<>();
    }

    public void addPassengerDetails(String passenDetails, int tic, int passengerId) {
        passengerDetails.add(passenDetails);
        passengerIds.add(passengerId);
        passengerCost.add(price * tic);
        price += 200 * tic;
        tickets -= tic;
        bookedTickets.add(tic);
        System.out.println("Booked Successfully...");
    }

    public void flightSummary() {
        System.out.println("Flight id is " + flightId + "     " + "Remaining Tickets available:" + tickets +
                "  Current Ticket Price " + price);
    }

    public void cancelTicket(int passengerId) {
        int index = passengerIds.indexOf(passengerId);
        if (index < 0) {
            System.out.println("Passenger Id is not found");
            return;
        }
        int ticketsCancel = bookedTickets.get(index);
        tickets += ticketsCancel;
        price -= 200 * ticketsCancel;
        bookedTickets.remove(index);
        passengerDetails.remove(index);
        passengerCost.remove(index);
        passengerIds.remove(index);
        System.out.println("Cancelled Booked Tickets Successfully!");
    }

    public void printSummary() {
        System.out.println("Flight=> " + flightId + "=> ");
        for (String detail : passengerDetails)
            System.out.println(detail);
    }
}
