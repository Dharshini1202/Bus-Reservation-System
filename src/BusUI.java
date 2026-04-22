import dao.BusDAO;
import dao.BookingDAO;
import model.Bus;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BusUI extends JFrame {

    JTextArea output;
    BusDAO busDAO = new BusDAO();
    BookingDAO bookingDAO = new BookingDAO();

    public BusUI() {

        setTitle("Bus Reservation System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text area
        output = new JTextArea();
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Buttons panel
        JPanel panel = new JPanel();

        JButton viewBtn = new JButton("View Buses");
        JButton addBtn = new JButton("Add Bus");
        JButton bookBtn = new JButton("Book Ticket");

        panel.add(viewBtn);
        panel.add(addBtn);
        panel.add(bookBtn);

        add(panel, BorderLayout.SOUTH);

        // Actions

        viewBtn.addActionListener(e -> viewBuses());

        addBtn.addActionListener(e -> addBus());

        bookBtn.addActionListener(e -> bookTicket());
    }

    private void viewBuses() {
        output.setText("");

        List<Bus> buses = busDAO.getAllBuses();

        for (Bus b : buses) {
            output.append(
                    b.getBusId() + " | " +
                            b.getBusName() + " | " +
                            b.getSource() + " -> " +
                            b.getDestination() + " | Seats: " +
                            b.getAvailableSeats() + "\n"
            );
        }
    }

    private void addBus() {
        String name = JOptionPane.showInputDialog("Enter Bus Name:");
        String source = JOptionPane.showInputDialog("Enter Source:");
        String destination = JOptionPane.showInputDialog("Enter Destination:");
        int seats = Integer.parseInt(JOptionPane.showInputDialog("Enter Seats:"));

        busDAO.addBus(name, source, destination, seats);
        viewBuses();
    }

    private void bookTicket() {
        String name = JOptionPane.showInputDialog("Enter your name:");
        int busId = Integer.parseInt(JOptionPane.showInputDialog("Enter Bus ID:"));
        int seats = Integer.parseInt(JOptionPane.showInputDialog("Enter Seats:"));

        bookingDAO.bookTicket(busId, name, seats);
        viewBuses();
    }

    public static void main(String[] args) {
        new BusUI().setVisible(true);
    }
}