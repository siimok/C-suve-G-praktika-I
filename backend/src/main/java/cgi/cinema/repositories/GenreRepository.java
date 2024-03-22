package cgi.cinema.repositories;

import cgi.cinema.domain.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    List<GenreEntity> findByIdIn(List<Long> genreIds);

    GenreEntity findByName(String name);

    boolean existsByName(String name);
}

