package cgi.cinema.mappers.impl;

import cgi.cinema.domain.dto.GenreDto;
import cgi.cinema.domain.entities.GenreEntity;
import cgi.cinema.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreMapperImpl implements Mapper<GenreEntity, GenreDto> {

    private ModelMapper modelMapper;

    public GenreMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GenreDto mapTo(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDto.class);
    }

    @Override
    public GenreEntity mapFrom(GenreDto genreDto) {
        return modelMapper.map(genreDto, GenreEntity.class);
    }
}
