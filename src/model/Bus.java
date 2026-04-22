package model;

public class Bus {

    private int busId;
    private String busName;
    private String source;
    private String destination;
    private int availableSeats;

    public Bus(int busId, String busName, String source, String destination, int availableSeats) {
        this.busId = busId;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public int getBusId() { return busId; }
    public String getBusName() { return busName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}