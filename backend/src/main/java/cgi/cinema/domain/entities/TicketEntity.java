package cgi.cinema.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name="session_id", nullable=false)
    private SessionEntity session;

    //This cinema has one viewing room.
    //7 rows
    private Integer rowNumber;

    //10 seats in a row
    private Integer seatNumber;
}
