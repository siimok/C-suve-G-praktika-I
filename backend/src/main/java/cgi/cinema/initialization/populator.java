package cgi.cinema.initialization;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

//ChatGPT was used.
//Should be different Data initializers for each entity,
//But I couldn't get them running in right order or making connections between them.
@Component
public class populator {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public populator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void populateMovieGenre() throws IOException {
        // Query to check if there are any movies in the database
        List<String> movies = jdbcTemplate.queryForList("SELECT name FROM movie", String.class);

        // If there are no movies in the database, run the populate_db.sql script
        if (movies.isEmpty()) {
            ClassPathResource resource = new ClassPathResource("populate_db.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String sqlScript = reader.lines().collect(Collectors.joining("\n"));

            jdbcTemplate.execute(sqlScript);

        }
    }
}
