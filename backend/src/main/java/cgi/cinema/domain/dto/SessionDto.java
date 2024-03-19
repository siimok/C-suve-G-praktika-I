package cgi.cinema.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDto {
    private Long id;

    private Long movie_id;

    private LocalDateTime startTime;

    private List<TicketDto> tickets;
}
