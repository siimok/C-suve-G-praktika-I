package cgi.cinema.initialization;

import cgi.cinema.domain.entities.GenreEntity;
import cgi.cinema.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GenreInitializer implements CommandLineRunner {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreInitializer(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {
        List<String> genreNames = Arrays.asList(
                "action", "adventure", "comedy", "drama", "fantasy", "horror",
                "musicals", "mystery", "romance", "science fiction", "sports",
                "thriller", "western"
        );

        for (String name : genreNames) {
            GenreEntity genre = new GenreEntity(name);
            genreRepository.save(genre);
        }
    }
}
