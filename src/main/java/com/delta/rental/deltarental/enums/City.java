package com.delta.rental.deltarental.enums;

public enum City {
    ISTANBUL("İstanbul"),
    ANKARA("Ankara"),
    IZMIR("İzmir"),
    BURSA("Bursa");

    private final String city;
    City(String city) {
        this.city=city;
    }

    public String getCityName() {
        return city;
    }
}

