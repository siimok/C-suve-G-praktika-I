package cgi.cinema.services.impl;

import cgi.cinema.domain.entities.TicketEntity;
import cgi.cinema.repositories.TicketRepository;
import cgi.cinema.services.TicketServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketServicesImpl implements TicketServices {
    private TicketRepository ticketRepository;

    public TicketServicesImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketEntity save(TicketEntity ticketEntity) {
        return ticketRepository.save(ticketEntity);
    }

    @Override
    public List<TicketEntity> findAll() {
        return StreamSupport.stream(ticketRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TicketEntity> findOne(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return ticketRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

}
