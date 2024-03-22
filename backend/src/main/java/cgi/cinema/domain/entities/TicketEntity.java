package cgi.cinema.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ticket", uniqueConstraints = @UniqueConstraint(columnNames = {"session_id", "row_number", "seat_number"}))
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    //This cinema has one viewing room.
    //7 rows
    private Integer rowNumber;

    //10 seats in a row
    private Integer seatNumber;
}
