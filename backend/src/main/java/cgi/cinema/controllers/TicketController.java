package cgi.cinema.controllers;

import cgi.cinema.domain.dto.TicketDto;
import cgi.cinema.domain.entities.SessionEntity;
import cgi.cinema.domain.entities.TicketEntity;
import cgi.cinema.mappers.Mapper;
import cgi.cinema.services.SessionServices;
import cgi.cinema.services.TicketServices;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketController {
    private TicketServices ticketServices;

    private SessionServices sessionServices;

    private Mapper<TicketEntity, TicketDto> ticketMapper;

    public TicketController(TicketServices ticketServices, SessionServices sessionServices, Mapper<TicketEntity, TicketDto> ticketMapper) {
        this.ticketServices = ticketServices;
        this.ticketMapper = ticketMapper;
        this.sessionServices = sessionServices;
    }

    @PostMapping(path = "/tickets")
    public ResponseEntity<List<TicketDto>> createTicket(@RequestBody List<TicketDto> ticketDtos) throws ChangeSetPersister.NotFoundException {
        List<TicketDto> createdTickets = new ArrayList<>();
        for (TicketDto ticketDto : ticketDtos) {
            if (!sessionServices.isExists(ticketDto.getSessionId())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            SessionEntity session = sessionServices.findOne(ticketDto.getSessionId()).orElseThrow(ChangeSetPersister.NotFoundException::new);

            TicketEntity ticketEntity = ticketMapper.mapFrom(ticketDto);
            ticketEntity.setSession(session);

            TicketEntity savedTicketEntity = ticketServices.save(ticketEntity);
            createdTickets.add(ticketMapper.mapTo(savedTicketEntity));
        }
        return new ResponseEntity<>(createdTickets, HttpStatus.CREATED);
    }

    @GetMapping(path = "/tickets")
    public List<TicketDto> listTickets() {
        List<TicketEntity> tickets = ticketServices.findAll();
        return tickets.stream()
                .map(ticketMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/tickets/{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable("id") Long id) {
        Optional<TicketEntity> foundTicket = ticketServices.findOne(id);
        return foundTicket.map(ticketEntity -> {
            TicketDto ticketDto = ticketMapper.mapTo(ticketEntity);
            return new ResponseEntity<>(ticketDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/tickets/{id}")
    public ResponseEntity<TicketDto> fullUpdateTicket(
            @PathVariable("id") Long id,
            @RequestBody TicketDto ticketDto) {

        if (!ticketServices.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ticketDto.setId(id);
        TicketEntity ticketEntity = ticketMapper.mapFrom(ticketDto);
        TicketEntity savedTicketEntity = ticketServices.save(ticketEntity);
        return new ResponseEntity<>(
                ticketMapper.mapTo(savedTicketEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/tickets/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable("id") Long id) {
        ticketServices.delete(id);
    }
}
