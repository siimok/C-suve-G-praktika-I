package cgi.cinema.controllers;

import cgi.cinema.domain.dto.SessionDto;
import cgi.cinema.domain.entities.SessionEntity;
import cgi.cinema.mappers.Mapper;
import cgi.cinema.services.SessionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SessionController {
    private SessionServices sessionServices;

    private Mapper<SessionEntity, SessionDto> sessionMapper;

    public SessionController(SessionServices sessionServices, Mapper<SessionEntity, SessionDto> sessionMapper) {
        this.sessionServices = sessionServices;
        this.sessionMapper = sessionMapper;
    }

    @PostMapping(path = "/sessions")
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionDto session) {
        SessionEntity sessionEntity = sessionMapper.mapFrom(session);
        SessionEntity savedSessionEntity = sessionServices.save(sessionEntity);
        return new ResponseEntity<>(sessionMapper.mapTo(savedSessionEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/sessions")
    public List<SessionDto> listSessions() {
        List<SessionEntity> sessions = sessionServices.findAll();
        return sessions.stream()
                .map(sessionMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/sessions/{id}")
    public ResponseEntity<SessionDto> getSession(@PathVariable("id") Long id) {
        Optional<SessionEntity> foundSession = sessionServices.findOne(id);
        return foundSession.map(sessionEntity -> {
            SessionDto sessionDto = sessionMapper.mapTo(sessionEntity);
            return new ResponseEntity<>(sessionDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/sessions/{id}")
    public ResponseEntity<SessionDto> fullUpdateSession(
            @PathVariable("id") Long id,
            @RequestBody SessionDto sessionDto) {

        if(!sessionServices.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        sessionDto.setId(id);
        SessionEntity sessionEntity = sessionMapper.mapFrom(sessionDto);
        SessionEntity savedSessionEntity = sessionServices.save(sessionEntity);
        return new ResponseEntity<>(
                sessionMapper.mapTo(savedSessionEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/sessions/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable("id") Long id) {
        sessionServices.delete(id);
    }
}
