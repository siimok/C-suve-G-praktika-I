package cgi.cinema.domain.dto;

import cgi.cinema.domain.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Long id;

    private String name;

    private Float rating;

    private Integer published;

    private Integer minimumAge;

    private Language language;

    private String description;

    private String imageUrl;

    private Optional<List<Long>> genreIds;

    private Optional<List<GenreDto>> genres;

    private Optional<List<SessionDto>> sessions;

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", published=" + published +
                ", minimumAge=" + minimumAge +
                ", language=" + language +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", genreIds=" + (genreIds.isPresent() ? genreIds.get() : "null") +
                ", genres=" + (genres.isPresent() ? genres.get() : "null") +
                ", sessions=" + (sessions.isPresent() ? sessions.get() : "null") +
                '}';
    }
}
