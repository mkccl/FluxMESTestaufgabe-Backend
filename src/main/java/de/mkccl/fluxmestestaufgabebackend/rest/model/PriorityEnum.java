package de.mkccl.fluxmestestaufgabebackend.rest.model;

public enum PriorityEnum {
    HIGH(0),
    MEDIUM(1),
    LOW(2);

    public final int value;

    PriorityEnum(int value) {
        this.value = value;
    }
}
