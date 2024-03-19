package cgi.cinema.repositories;

import cgi.cinema.domain.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    Set<GenreEntity> findByIdIn(List<Long> genreIds);
}

