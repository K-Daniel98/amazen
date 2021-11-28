package hu.unideb.web.amazen.core.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPriceException extends ResponseStatusException {
    public InvalidPriceException() {
        super(HttpStatus.BAD_REQUEST, "Price cannot be negative");
    }
}