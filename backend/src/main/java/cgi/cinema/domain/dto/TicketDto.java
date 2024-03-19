package cgi.cinema.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    private Long id;

    private Long SessionId;

    //This cinema has one viewing room.
    //7 rows
    private Integer rowNumber;

    //10 seats in a row
    private Integer seatNumber;
}
