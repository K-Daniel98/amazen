package hu.unideb.web.amazen.core.item.service;

import hu.unideb.web.amazen.core.item.dto.ItemDto;
import hu.unideb.web.amazen.core.item.entity.Item;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<ItemDto> getItems();
    Optional<ItemDto> getItem(Long id);
    ItemDto createItem(HttpServletRequest request, ItemDto itemDto);
    void deleteItem(HttpServletRequest request, Long id);
    ItemDto updateItem(HttpServletRequest request, ItemDto itemDto);

}
