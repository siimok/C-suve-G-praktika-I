package cgi.cinema.controllers;

import cgi.cinema.domain.dto.GenreDto;
import cgi.cinema.domain.entities.GenreEntity;
import cgi.cinema.mappers.Mapper;
import cgi.cinema.services.GenreServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GenreController {
    private GenreServices genreServices;

    private Mapper<GenreEntity, GenreDto> genreMapper;

    public GenreController(GenreServices genreServices, Mapper<GenreEntity, GenreDto> genreMapper) {
        this.genreServices = genreServices;
        this.genreMapper = genreMapper;
    }

    @PostMapping(path = "/genres")
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genre) {
        GenreEntity genreEntity = genreMapper.mapFrom(genre);
        GenreEntity savedGenreEntity = genreServices.save(genreEntity);
        return new ResponseEntity<>(genreMapper.mapTo(savedGenreEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/genres")
    public List<GenreDto> listGenres() {
        List<GenreEntity> genres = genreServices.findAll();
        return genres.stream()
                .map(genreMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/genres/{id}")
    public ResponseEntity<GenreDto> getGenre(@PathVariable("id") Long id) {
        Optional<GenreEntity> foundGenre = genreServices.findOne(id);
        return foundGenre.map(genreEntity -> {
            GenreDto genreDto = genreMapper.mapTo(genreEntity);
            return new ResponseEntity<>(genreDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/genres/{id}")
    public ResponseEntity<GenreDto> fullUpdateGenre(
            @PathVariable("id") Long id,
            @RequestBody GenreDto genreDto) {

        if (!genreServices.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        genreDto.setId(id);
        GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
        GenreEntity savedGenreEntity = genreServices.save(genreEntity);
        return new ResponseEntity<>(
                genreMapper.mapTo(savedGenreEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/genres/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable("id") Long id) {
        genreServices.delete(id);
    }
}
