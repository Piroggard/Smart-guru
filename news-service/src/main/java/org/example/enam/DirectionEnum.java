package org.example.enam;

public enum DirectionEnum {

    DIRECTION1("Description for Direction1"),
    DIRECTION2("Description for Direction2");

    private final String description;

    DirectionEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

