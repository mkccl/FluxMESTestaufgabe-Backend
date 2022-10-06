package de.mkccl.fluxmestestaufgabebackend.rest.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "ticket")
public class TicketModel {

    @Id
    private String ticketId;

    @NotNull
    private String title;
    private String description;

    @NotNull
    private int priority;

    @NotNull
    private int status;

    public TicketModel(String title, String description, int priority ) {
        this.ticketId = UUID.randomUUID().toString();

        this.priority = priority;
        this.title = title;
        this.status = StatusEnum.NEW.value;

        if (null == description) {
            this.description = "";
        } else {
            this.description = description;
        }

    }

}
