package de.mkccl.fluxmestestaufgabebackend.rest.service;

import de.mkccl.fluxmestestaufgabebackend.rest.dto.TicketDto;
import de.mkccl.fluxmestestaufgabebackend.rest.model.PriorityEnum;
import de.mkccl.fluxmestestaufgabebackend.rest.model.StatusEnum;
import de.mkccl.fluxmestestaufgabebackend.rest.model.TicketModel;
import de.mkccl.fluxmestestaufgabebackend.rest.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockService {

    private final TicketService ticketService;


    public MockService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void create100Tickets() {
        List<TicketModel> tickets = new ArrayList<>();

        for (int i = 0; i < 100 ; i++) {
            tickets.add(new TicketModel("testTitle" + i , "testTitle" + i, PriorityEnum.HIGH.value));
        }

        ticketService.saveTickets(tickets);
    }

    public void create20Tickets() {
        List<TicketModel> tickets = new ArrayList<>();

        for (int i = 0; i < 20 ; i++) {
            tickets.add(new TicketModel("testTitle" + i , "testTitle" + i, PriorityEnum.HIGH.value));
        }

        ticketService.saveTickets(tickets);
    }

}
