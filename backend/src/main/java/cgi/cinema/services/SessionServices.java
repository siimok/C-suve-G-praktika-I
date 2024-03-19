package cgi.cinema.services;

import cgi.cinema.domain.entities.SessionEntity;

import java.util.List;
import java.util.Optional;

public interface SessionServices {
    SessionEntity save(SessionEntity sessionEntity);

    List<SessionEntity> findAll();

    Optional<SessionEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
