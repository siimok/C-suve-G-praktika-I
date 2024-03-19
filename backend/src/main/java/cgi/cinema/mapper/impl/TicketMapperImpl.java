package cgi.cinema.mapper.impl;

import cgi.cinema.domain.dto.TicketDto;
import cgi.cinema.domain.entities.TicketEntity;
import cgi.cinema.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapperImpl implements Mapper<TicketEntity, TicketDto> {

    private ModelMapper modelMapper;

    public TicketMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TicketDto mapTo(TicketEntity ticketEntity) {
        return modelMapper.map(ticketEntity, TicketDto.class);
    }

    @Override
    public TicketEntity mapFrom(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, TicketEntity.class);
    }
}


