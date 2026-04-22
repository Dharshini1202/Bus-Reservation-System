package dao;

import model.Bus;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM buses";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("Bus_ID"),
                        rs.getString("Bus_Name"),
                        rs.getString("Source"),
                        rs.getString("Destination"),
                        rs.getInt("Available_Seats")
                );
                buses.add(bus);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buses;
    }

    public void addBus(String name, String source, String destination, int seats) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO buses (Bus_Name, Source, Destination, Available_Seats) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, source);
            ps.setString(3, destination);
            ps.setInt(4, seats);

            ps.executeUpdate();

            System.out.println("✅ Bus added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}