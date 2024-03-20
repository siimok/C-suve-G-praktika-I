package cgi.cinema.repositories;

import cgi.cinema.domain.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

    @Query("SELECT m FROM MovieEntity m LEFT JOIN FETCH m.genres WHERE m.id = :id")
    Optional<MovieEntity> findMovieWithGenresById(@Param("id") Long id);

}