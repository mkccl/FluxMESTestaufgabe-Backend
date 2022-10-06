package de.mkccl.fluxmestestaufgabebackend.rest.model;

public enum StatusEnum {

    NEW(0),
    IN_PROGRESS(1),
    DONE(2);


    public final int value;

    StatusEnum(int value) {
        this.value = value;
    }


}
