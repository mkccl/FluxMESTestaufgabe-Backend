package de.mkccl.fluxmestestaufgabebackend.rest.controller;

import de.mkccl.fluxmestestaufgabebackend.rest.dto.TicketDto;
import de.mkccl.fluxmestestaufgabebackend.rest.model.PriorityEnum;
import de.mkccl.fluxmestestaufgabebackend.rest.model.StatusEnum;
import de.mkccl.fluxmestestaufgabebackend.rest.model.TicketModel;
import de.mkccl.fluxmestestaufgabebackend.rest.repository.TicketRepository;
import de.mkccl.fluxmestestaufgabebackend.rest.service.MockService;
import de.mkccl.fluxmestestaufgabebackend.rest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final MockService mockService;

    @Autowired
    public TicketController(TicketService ticketService, MockService mockService) {
        this.ticketService = ticketService;
        this.mockService = mockService;
    }

    @PostMapping
    public ResponseEntity<TicketModel> createTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.createTicket(ticketDto);
    }

    @GetMapping
    public Page<TicketModel> getTicketPyPage(@RequestParam int page) {
        return ticketService.getTicketsByPage(page);
    }

    @GetMapping("/status")
    public Page<TicketModel> getTicketByPageAndStatus(
            @RequestParam int page,
            @RequestParam(required = false) StatusEnum status,
            @RequestParam(required = false) PriorityEnum priority,
            @RequestParam(required = false) String title
    ) {
        return ticketService.getTicketsByPageAndStatus(page, status, priority, title);
    }

    @GetMapping("/id/{ticketId}")
    public TicketModel getTicketByTicketId(@PathVariable String ticketId) {
        return ticketService.getTicketByTicketId(ticketId);
    }

    @PutMapping("/update/id/{ticketId}")
    public TicketModel updateTicketByTicketId(@RequestBody TicketDto ticketDto, @PathVariable String ticketId) {
        return ticketService.updateTicketById(ticketDto, ticketId);
    }

    @PostMapping("/mock/100")
    public void create100Tickets() {
        mockService.create100Tickets();
    }

    @PostMapping("/mock/20")
    public void create20Tickets() {
        mockService.create20Tickets();
    }


}
