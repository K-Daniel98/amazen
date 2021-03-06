package hu.unideb.web.amazen.core.item.controller;

import hu.unideb.web.amazen.core.item.dto.ItemDto;
import hu.unideb.web.amazen.core.item.entity.Item;
import hu.unideb.web.amazen.core.item.exception.ItemNotFoundException;
import hu.unideb.web.amazen.core.item.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDto> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public ItemDto get(@PathVariable Long id) {
        return itemService.getItem(id)
            .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SELLER')")
    public ItemDto create(HttpServletRequest req, @RequestBody ItemDto itemDto) {
        return itemService.createItem(req, itemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SELLER')")
    public void delete(HttpServletRequest req, @PathVariable Long id) {
        itemService.deleteItem(req, id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SELLER')")
    public ItemDto update(HttpServletRequest req, @RequestBody ItemDto itemDto) {
        return itemService.updateItem(req, itemDto);
    }

}
