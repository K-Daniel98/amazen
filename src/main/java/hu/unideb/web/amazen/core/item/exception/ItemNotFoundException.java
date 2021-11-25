package hu.unideb.web.amazen.core.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ItemNotFoundException extends ResponseStatusException {
    public ItemNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("Item with id '%d' does not exist", id));
    }
}
