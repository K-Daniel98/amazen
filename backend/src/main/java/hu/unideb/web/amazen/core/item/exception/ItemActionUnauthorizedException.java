package hu.unideb.web.amazen.core.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ItemActionUnauthorizedException extends ResponseStatusException {
    public ItemActionUnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "You are not allowed to modify this item");
    }
}