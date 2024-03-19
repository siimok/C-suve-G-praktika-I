package cgi.cinema.services.impl;

import cgi.cinema.domain.entities.MovieEntity;
import cgi.cinema.repositories.MovieRepository;
import cgi.cinema.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieEntity save(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieEntity> findAll() {
        return StreamSupport.stream(movieRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieEntity> findOne(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return movieRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
