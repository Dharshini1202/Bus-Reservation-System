import dao.BusDAO;
import dao.BookingDAO;
import model.Bus;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BusDAO busDAO = new BusDAO();
        BookingDAO bookingDAO = new BookingDAO();

        while (true) {
            System.out.println("\n1. View Buses");
            System.out.println("2. Add Bus");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    List<Bus> buses = busDAO.getAllBuses();
                    for (Bus b : buses) {
                        System.out.println(
                                b.getBusId() + " | " +
                                        b.getBusName() + " | " +
                                        b.getSource() + " -> " +
                                        b.getDestination() + " | Seats: " +
                                        b.getAvailableSeats()
                        );
                    }
                    break;

                case 2:
                    sc.nextLine();

                    System.out.print("Enter Bus Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Source: ");
                    String source = sc.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();

                    System.out.print("Enter Total Seats: ");
                    int seats = sc.nextInt();

                    busDAO.addBus(name, source, destination, seats);
                    break;

                case 3:
                    sc.nextLine();

                    System.out.print("Enter your name: ");
                    String userName = sc.nextLine();

                    System.out.print("Enter Bus ID: ");
                    int busId = sc.nextInt();

                    System.out.print("Enter seats: ");
                    int seatCount = sc.nextInt();

                    bookingDAO.bookTicket(busId, userName, seatCount);
                    break;

                case 4:
                    bookingDAO.viewBookings();
                    break;

                case 5:
                    System.out.print("Enter Booking ID to cancel: ");
                    int bookingId = sc.nextInt();

                    bookingDAO.cancelTicket(bookingId);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}