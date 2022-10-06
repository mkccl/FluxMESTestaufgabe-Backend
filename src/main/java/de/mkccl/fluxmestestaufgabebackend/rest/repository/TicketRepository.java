package de.mkccl.fluxmestestaufgabebackend.rest.repository;

import de.mkccl.fluxmestestaufgabebackend.rest.model.TicketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<TicketModel, String> {

    Page<TicketModel> getTicketModelByStatusEquals(Pageable pageable, int status);


    Page<TicketModel> getTicketModelByStatusEqualsAndTitleContainingIgnoreCaseAndPriorityEquals(Pageable page, int status, String title, int priority );

}
