package cgi.cinema.services;

import cgi.cinema.domain.entities.MovieEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieEntity save(MovieEntity movieEntity);

    List<MovieEntity> findAll();

    List<MovieEntity> findAllByCriteria(
            Long genreId,
            LocalDateTime startTime,
            Integer minimumAge,
            String keyword
    );

    Optional<MovieEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
