package BookingSystems.FlightBookingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightMain {
    public static void main(String[] args) {
        List<BookingFlight> flights = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            flights.add(new BookingFlight());
        }
        int passengerID = 1;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1.BookTicket 2.CancelTicket 3.PrintSummary");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Please Enter the Flight Id: ");
                    int fid = sc.nextInt();
                    if (fid > flights.size() || fid <= 0) {
                        System.out.println("Flight is not found in our list");
                        break;
                    }
                    BookingFlight curFlight = null;
                    for (BookingFlight f : flights) {
                        if (f.flightId == fid) {
                            curFlight = f;
                            f.flightSummary();
                            break;
                        }
                    }
                    if (curFlight == null) {
                        System.out.println("Flight is not found");
                        break;
                    }
                    System.out.println("Enter the no of tickets: ");
                    int tickets = sc.nextInt();

                    if (tickets > curFlight.tickets) {
                        System.out.println("Not Enough Tickets in Our Flight");
                        break;
                    }
                    book(curFlight, tickets, passengerID);
                    passengerID += 1;
                    break;
                }

                case 2: {
                    System.out.println("Enter the flight id and passenger Id to cancel: ");
                    int fid = sc.nextInt();
                    if (fid > flights.size() || fid <= 0) {
                        System.out.println("Invalid flight Id. Try again: ");
                        break;
                    }
                    BookingFlight curBookingFlight = null;
                    for (BookingFlight f : flights) {
                        if (f.flightId == fid) {
                            curBookingFlight = f;
                            break;
                        }
                    }
                    if (curBookingFlight == null) {
                        System.out.println("Flight is not found");
                        break;
                    }
                    int id = sc.nextInt();
                    cancelTicket(curBookingFlight, id);
                    break;
                }

                case 3: {
                    for (BookingFlight f : flights) {
                        if (f.passengerDetails.size() == 0) {
                            System.out.println("No Passenger Id for the flight: " + f.flightId);
                        } else {
                            printSummary(f);
                        }
                    }
                    break;
                }

                default: {
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }
            }
        }
    }

    private static void printSummary(BookingFlight f) {
        f.printSummary();
    }

    private static void cancelTicket(BookingFlight curBookingFlight, int id) {
        curBookingFlight.cancelTicket(id);
        curBookingFlight.flightSummary();
        curBookingFlight.printSummary();
    }

    private static void book(BookingFlight curFlight, int tickets, int passengerID) {
        String passengerDetails = "";
        passengerDetails = "Passenger id: " + passengerID + "--" + "No Of Tickets Purchased: " + tickets + "--" + "TotalPrice: " + curFlight.price * tickets;

        curFlight.addPassengerDetails(passengerDetails, tickets, passengerID);
        curFlight.flightSummary();
        curFlight.printSummary();
    }
}
