package com.delta.rental.deltarental.enums;

public enum City {
    ISTANBUL("06"),
    ANKARA("Ankara"),
    IZMIR("İzmir"),
    BURSA("Bursa");

    private final String description;
    City(String description) {
        this.description=description;
    }

    public String getCityName() {
        return description;
    }
}

