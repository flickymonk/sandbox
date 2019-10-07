package com.alevel.datewizard.model;

public enum DayOfWeek {

    MON("Monday"),

    TUE("Tuesday"),

    WED("Wednesday"),

    THR("Thursday"),

    FRI("Friday"),

    SAT("Saturday"),

    SUN("Sunday");

    private final String displayName;

    DayOfWeek(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
