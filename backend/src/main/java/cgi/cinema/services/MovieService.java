package cgi.cinema.services;

import cgi.cinema.domain.entities.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieEntity save(MovieEntity movieEntity);

    List<MovieEntity> findAll();

    Optional<MovieEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
