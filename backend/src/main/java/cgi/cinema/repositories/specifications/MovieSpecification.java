package cgi.cinema.repositories.specifications;

import cgi.cinema.domain.entities.MovieEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//Generate by ChatGPT
public class MovieSpecification {
    public static Specification<MovieEntity> filterMovies(Long genreId, LocalDateTime startTime, Integer minimumAge, String keyword) {
        return (Root<MovieEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (genreId != null) {
                predicates.add(cb.equal(root.join("genres").get("id"), genreId));
            }
            if (startTime != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.join("sessions").get("startTime"), startTime));
            }
            if (minimumAge != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("minimumAge"), minimumAge));
            }
            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
