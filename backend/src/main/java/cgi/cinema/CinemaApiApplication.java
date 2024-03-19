package cgi.cinema;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class CinemaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApiApplication.class, args);
    }
}
