package cgi.cinema.services.impl;

import cgi.cinema.domain.entities.GenreEntity;
import cgi.cinema.repositories.GenreRepository;
import cgi.cinema.services.GenreServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenreServiceImpl implements GenreServices {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreEntity save(GenreEntity genreEntity) {
        return genreRepository.save(genreEntity);
    }

    @Override
    public List<GenreEntity> findAll() {
        return StreamSupport.stream(genreRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GenreEntity> findOne(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<GenreEntity> findMultiple(List<Long> ids) {
        return genreRepository.findByIdIn(ids);
    }

    @Override
    public boolean isExists(Long id) {
        return genreRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
