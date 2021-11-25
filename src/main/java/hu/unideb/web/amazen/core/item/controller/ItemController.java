package hu.unideb.web.amazen.core.item.controller;

import hu.unideb.web.amazen.core.item.entity.Item;
import hu.unideb.web.amazen.core.item.exception.ItemNotFoundException;
import hu.unideb.web.amazen.core.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    private List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    private Item getItem(@PathVariable Long id) {
        return itemService.getItem(id)
            .orElseThrow(() -> new ItemNotFoundException(id));
    }

}
