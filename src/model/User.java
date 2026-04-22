package model;

public class User {

    private int userId;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}