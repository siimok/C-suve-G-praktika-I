package cgi.cinema.services;

import cgi.cinema.domain.entities.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface TicketServices {
    TicketEntity save(TicketEntity ticketEntity);

    List<TicketEntity> findAll();

    Optional<TicketEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
