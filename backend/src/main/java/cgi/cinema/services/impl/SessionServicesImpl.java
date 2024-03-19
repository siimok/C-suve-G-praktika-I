package cgi.cinema.services.impl;

import cgi.cinema.domain.entities.SessionEntity;
import cgi.cinema.repositories.SessionRepository;
import cgi.cinema.services.SessionServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SessionServicesImpl implements SessionServices {
    private SessionRepository sessionRepository;

    public SessionServicesImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public SessionEntity save(SessionEntity sessionEntity) {
        return sessionRepository.save(sessionEntity);
    }

    @Override
    public List<SessionEntity> findAll() {
        return StreamSupport.stream(sessionRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SessionEntity> findOne(Long id) {
        return sessionRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return sessionRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }
}
