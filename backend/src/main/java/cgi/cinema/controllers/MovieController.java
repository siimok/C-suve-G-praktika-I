package cgi.cinema.controllers;

import cgi.cinema.domain.dto.GenreDto;
import cgi.cinema.domain.dto.MovieDto;
import cgi.cinema.domain.dto.SessionDto;
import cgi.cinema.domain.entities.GenreEntity;
import cgi.cinema.domain.entities.MovieEntity;
import cgi.cinema.domain.entities.SessionEntity;
import cgi.cinema.mappers.Mapper;
import cgi.cinema.services.GenreServices;
import cgi.cinema.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MovieController {

    private MovieService movieService;

    private GenreServices genreServices;

    private Mapper<MovieEntity, MovieDto> movieMapper;

    private Mapper<GenreEntity, GenreDto> genreMapper;

    private Mapper<SessionEntity, SessionDto> sessionMapper;

    public MovieController(MovieService movieService,
                           GenreServices genreServices,
                           Mapper<MovieEntity, MovieDto> movieMapper,
                           Mapper<GenreEntity, GenreDto> genreMapper,
                           Mapper<SessionEntity, SessionDto> sessionMapper
    ) {
        this.movieService = movieService;
        this.genreServices = genreServices;
        this.movieMapper = movieMapper;
        this.genreMapper = genreMapper;
        this.sessionMapper = sessionMapper;
    }

    @PostMapping(path = "/movies")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movie) {

        List<GenreEntity> genreEntities = new ArrayList<>();

        if (movie.getGenreIds().isPresent()) {
            List<Long> genreIds = movie.getGenreIds().get();
            genreEntities = genreServices.findMultiple(genreIds);
        }

        MovieEntity movieEntity = movieMapper.mapFrom(movie);
        movieEntity.setGenres(genreEntities);

        MovieEntity savedMovieEntity = movieService.save(movieEntity);
        return new ResponseEntity<>(movieMapper.mapTo(savedMovieEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/movies")
    public List<MovieDto> listMovies(
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) Integer minimumAge,
            @RequestParam(required = false) String title
    ) {
        List<MovieEntity> movies = movieService.findAllByCriteria(genreId, startTime, minimumAge, title);
        return movies.stream()
                .map(movie -> {
                    MovieDto movieDto = movieMapper.mapTo(movie);
                    List<GenreDto> genreDtos = movie.getGenres().stream()
                            .map(genreMapper::mapTo)
                            .collect(Collectors.toList());
                    movieDto.setGenres(Optional.of(genreDtos));
                    // Convert SessionEntity to SessionDto and set an empty list of tickets
                    List<SessionDto> sessionDtos = movie.getSessions().stream()
                            .map(session -> {
                                SessionDto sessionDto = sessionMapper.mapTo(session); // Assuming you have a sessionMapper
                                sessionDto.setTickets(Collections.emptyList()); // Set tickets to empty list
                                return sessionDto;
                            })
                            .collect(Collectors.toList());
                    movieDto.setSessions(Optional.of(sessionDtos));
                    return movieDto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/movies/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("id") Long id) {
        Optional<MovieEntity> foundMovie = movieService.findOne(id);
        return foundMovie.map(movieEntity -> {
            MovieDto movieDto = movieMapper.mapTo(movieEntity);
            List<GenreDto> genreDtos = movieEntity.getGenres().stream()
                    .map(genreMapper::mapTo)
                    .collect(Collectors.toList());
            movieDto.setGenres(Optional.of(genreDtos));
            return new ResponseEntity<>(movieDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/movies/{id}")
    public ResponseEntity<MovieDto> fullUpdateMovie(
            @PathVariable("id") Long id,
            @RequestBody MovieDto movieDto) {

        if (!movieService.isExists(id)) {
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
