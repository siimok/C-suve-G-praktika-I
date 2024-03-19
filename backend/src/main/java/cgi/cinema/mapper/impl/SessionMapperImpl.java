package cgi.cinema.mapper.impl;

import cgi.cinema.domain.dto.SessionDto;
import cgi.cinema.domain.entities.SessionEntity;
import cgi.cinema.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionMapperImpl implements Mapper<SessionEntity, SessionDto> {

    private ModelMapper modelMapper;

    public SessionMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SessionDto mapTo(SessionEntity sessionEntity) {
        return modelMapper.map(sessionEntity, SessionDto.class);
    }

    @Override
    public SessionEntity mapFrom(SessionDto sessionDto) {
        return modelMapper.map(sessionDto, SessionEntity.class);
    }
}


