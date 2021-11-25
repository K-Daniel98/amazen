package hu.unideb.web.amazen.core.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException() {
        super(HttpStatus.FORBIDDEN, "A user with the given username already exists");
    }
}
