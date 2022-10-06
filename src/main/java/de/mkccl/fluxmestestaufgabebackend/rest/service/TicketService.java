package de.mkccl.fluxmestestaufgabebackend.rest.service;

import de.mkccl.fluxmestestaufgabebackend.rest.dto.TicketDto;
import de.mkccl.fluxmestestaufgabebackend.rest.model.PriorityEnum;
import de.mkccl.fluxmestestaufgabebackend.rest.model.StatusEnum;
import de.mkccl.fluxmestestaufgabebackend.rest.model.TicketModel;
import de.mkccl.fluxmestestaufgabebackend.rest.repository.TicketRepository;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Page<TicketModel> getTicketsByPage(int page) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("status"));
        return ticketRepository.findAll(pageRequest);
    }

    public Page<TicketModel> getTicketsByPageAndStatus(int page, StatusEnum status, PriorityEnum priority, String title) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return ticketRepository.getTicketModelByStatusEquals(pageRequest, status.value);
    }

    public TicketModel updateTicketById(TicketDto ticketDto, String ticketId) {
        TicketModel ticketModel = ticketRepository.findById(ticketId).orElse(null);

        if (null != ticketModel) {
            ticketModel.setDescription(ticketDto.getDescription());
            ticketModel.setPriority(ticketDto.getPriority());
            ticketModel.setStatus(ticketDto.getStatus());
            ticketModel.setTitle(ticketDto.getTitle());

            return ticketRepository.save(ticketModel);
        } else {
            return null;
        }
    }

    public ResponseEntity<TicketModel> createTicket(TicketDto ticketDto) {
        if (!isTicketValid(ticketDto)) {
            return ResponseEntity.status(400).body(null);
        }
        TicketModel ticketModel = new TicketModel(ticketDto.getTitle(), ticketDto.getDescription(), ticketDto.getPriority());
        return ResponseEntity.ok(ticketRepository.save(ticketModel));
    }

    public TicketModel getTicketByTicketId(String ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }
    
    public void saveTickets(List<TicketModel> tickets) {
        ticketRepository.saveAll(tickets);
    }

    private boolean isTicketValid(TicketDto ticketDto) {
        return null != ticketDto.getTitle();
    }

}
