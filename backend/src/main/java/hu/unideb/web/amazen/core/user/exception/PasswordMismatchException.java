package hu.unideb.web.amazen.core.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordMismatchException extends ResponseStatusException {
    public PasswordMismatchException() {
        super(HttpStatus.BAD_REQUEST, "Invalid password");
    }
}