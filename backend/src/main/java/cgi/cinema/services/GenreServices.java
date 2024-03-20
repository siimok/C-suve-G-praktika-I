package cgi.cinema.services;

import cgi.cinema.domain.entities.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreServices {
    GenreEntity save(GenreEntity genreEntity);

    List<GenreEntity> findAll();

    Optional<GenreEntity> findOne(Long id);

    List<GenreEntity> findMultiple(List<Long> ids);

    boolean isExists(Long id);

    void delete(Long id);
}
