package com.delta.rental.deltarental.enums;

public enum GearType {
    MANUEL("Manuel"),
    AUTO("Auto");

    private final String gear;
    GearType(String gear) {
        this.gear=gear;
    }

    public String getGearType() {
        return gear;
    }
}
