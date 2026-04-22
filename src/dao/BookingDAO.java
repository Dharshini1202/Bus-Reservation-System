package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingDAO {

    public void bookTicket(int busId, String userName, int seats) {

        try {
            Connection con = DBConnection.getConnection();

            // Check seats
            String checkQuery = "SELECT Available_Seats FROM buses WHERE Bus_ID = ?";
            PreparedStatement psCheck = con.prepareStatement(checkQuery);
            psCheck.setInt(1, busId);

            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("Available_Seats");

                if (available < seats) {
                    System.out.println("❌ Not enough seats available!");
                    return;
                }

                // Insert user
                String userQuery = "INSERT INTO Users (Name) VALUES (?)";
                PreparedStatement ps1 = con.prepareStatement(userQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                ps1.setString(1, userName);
                ps1.executeUpdate();

                ResultSet rsKeys = ps1.getGeneratedKeys();
                rsKeys.next();
                int userId = rsKeys.getInt(1);

                // Insert booking
                String bookingQuery = "INSERT INTO Bookings (Bus_ID, User_ID, Booking_Date, Seats_Booked) VALUES (?, ?, CURDATE(), ?)";
                PreparedStatement ps2 = con.prepareStatement(bookingQuery);
                ps2.setInt(1, busId);
                ps2.setInt(2, userId);
                ps2.setInt(3, seats);
                ps2.executeUpdate();

                // Update seats
                String updateQuery = "UPDATE buses SET Available_Seats = Available_Seats - ? WHERE Bus_ID = ?";
                PreparedStatement ps3 = con.prepareStatement(updateQuery);
                ps3.setInt(1, seats);
                ps3.setInt(2, busId);
                ps3.executeUpdate();

                System.out.println("✅ Booking successful!");

            } else {
                System.out.println("❌ Bus not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBookings() {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM Bookings";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Booking ID: " + rs.getInt("Booking_ID") +
                                " | Bus ID: " + rs.getInt("Bus_ID") +
                                " | User ID: " + rs.getInt("User_ID") +
                                " | Seats: " + rs.getInt("Seats_Booked") +
                                " | Date: " + rs.getDate("Booking_Date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelTicket(int bookingId) {

        try {
            Connection con = DBConnection.getConnection();

            String getQuery = "SELECT Bus_ID, Seats_Booked FROM Bookings WHERE Booking_ID = ?";
            PreparedStatement ps1 = con.prepareStatement(getQuery);
            ps1.setInt(1, bookingId);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int busId = rs.getInt("Bus_ID");
                int seats = rs.getInt("Seats_Booked");

                String deleteQuery = "DELETE FROM Bookings WHERE Booking_ID = ?";
                PreparedStatement ps2 = con.prepareStatement(deleteQuery);
                ps2.setInt(1, bookingId);
                ps2.executeUpdate();

                String updateQuery = "UPDATE buses SET Available_Seats = Available_Seats + ? WHERE Bus_ID = ?";
                PreparedStatement ps3 = con.prepareStatement(updateQuery);
                ps3.setInt(1, seats);
                ps3.setInt(2, busId);
                ps3.executeUpdate();

                System.out.println("✅ Booking cancelled!");

            } else {
                System.out.println("❌ Booking not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}