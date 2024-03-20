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

    private Long movieId;

    private LocalDateTime startTime;

    private List<TicketDto> tickets;

    @Override
    public String toString() {
        return "SessionEntity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", tickets=" + (tickets != null ? tickets.size() : "null") + // Include ticket count instead of printing tickets directly
                ", movie=Id" + (movieId != null ? movieId : "null") + // Include movie ID instead of printing the whole movie object
                '}';
    }
}
