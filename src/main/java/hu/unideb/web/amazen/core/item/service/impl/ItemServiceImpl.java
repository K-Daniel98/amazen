package hu.unideb.web.amazen.core.item.service.impl;

import hu.unideb.web.amazen.core.item.entity.Item;
import hu.unideb.web.amazen.core.item.repository.ItemRepository;
import hu.unideb.web.amazen.core.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItem(Long id) {
        return itemRepository.findById(id);
    }
}
