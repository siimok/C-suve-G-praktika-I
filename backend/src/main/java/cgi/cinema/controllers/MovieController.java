package cgi.cinema.controllers;

import cgi.cinema.domain.dto.MovieDto;
import cgi.cinema.domain.entities.MovieEntity;
import cgi.cinema.mappers.Mapper;
import cgi.cinema.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    private MovieService movieService;

    private Mapper<MovieEntity, MovieDto> movieMapper;

    public MovieController(MovieService movieService, Mapper<MovieEntity, MovieDto> movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping(path = "/movies")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movie) {
        MovieEntity movieEntity = movieMapper.mapFrom(movie);
        MovieEntity savedMovieEntity = movieService.save(movieEntity);
        return new ResponseEntity<>(movieMapper.mapTo(savedMovieEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/movies")
    public List<MovieDto> listMovies() {
        List<MovieEntity> movies = movieService.findAll();
        return movies.stream()
                .map(movieMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/movies/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("id") Long id) {
        Optional<MovieEntity> foundMovie = movieService.findOne(id);
        return foundMovie.map(movieEntity -> {
            MovieDto movieDto = movieMapper.mapTo(movieEntity);
            return new ResponseEntity<>(movieDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/movies/{id}")
    public ResponseEntity<MovieDto> fullUpdateMovie(
            @PathVariable("id") Long id,
            @RequestBody MovieDto movieDto) {

        if(!movieService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        movieDto.setId(id);
        MovieEntity movieEntity = movieMapper.mapFrom(movieDto);
        MovieEntity savedMovieEntity = movieService.save(movieEntity);
        return new ResponseEntity<>(
                movieMapper.mapTo(savedMovieEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/movies/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.delete(id);
    }
}
