package hu.unideb.web.amazen.core.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ItemDeleteUnauthorizedException extends ResponseStatusException {
    public ItemDeleteUnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "You are not allowed to delete this item");
    }
}