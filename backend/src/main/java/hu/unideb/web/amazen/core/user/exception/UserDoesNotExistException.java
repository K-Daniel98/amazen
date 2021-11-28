package hu.unideb.web.amazen.core.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserDoesNotExistException extends ResponseStatusException {
    public UserDoesNotExistException() {
        super(HttpStatus.FORBIDDEN, "User does not exist");
    }
}