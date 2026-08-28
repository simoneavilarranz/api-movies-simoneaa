package dev.simoneaa.demop5.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class MovieExceptionNotFound extends MovieException {

    public MovieExceptionNotFound(String message) {
        super(message);
    }

    public MovieExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }
    
}
