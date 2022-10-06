package de.mkccl.fluxmestestaufgabebackend.rest.dto;

import de.mkccl.fluxmestestaufgabebackend.rest.model.PriorityEnum;
import de.mkccl.fluxmestestaufgabebackend.rest.model.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TicketDto {

    private Optional<String> ticketId;
    private String title;
    private String description;
    private int priority;
    private int status;


}
