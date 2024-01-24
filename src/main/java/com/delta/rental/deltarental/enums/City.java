package com.delta.rental.deltarental.enums;

public enum City {
    ankara(6),
    antalya(7),
    istanbul(34);

    private final int value;

    City(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

