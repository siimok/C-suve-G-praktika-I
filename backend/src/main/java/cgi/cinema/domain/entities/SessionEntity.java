package cgi.cinema.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessin_id_seq")
    private Long id;

    private Long movieId;

    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    private MovieEntity movie;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Set<TicketEntity> tickets = new HashSet<>();
}
