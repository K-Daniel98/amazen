package hu.unideb.web.amazen.core.item.service;

import hu.unideb.web.amazen.core.item.entity.Item;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ItemService {

    List<Item> getItems();
    Optional<Item> getItem(Long id);

}
