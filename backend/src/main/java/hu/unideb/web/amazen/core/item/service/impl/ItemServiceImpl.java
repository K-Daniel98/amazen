package hu.unideb.web.amazen.core.item.service.impl;

import hu.unideb.web.amazen.core.item.dto.ItemDto;
import hu.unideb.web.amazen.core.item.entity.Item;
import hu.unideb.web.amazen.core.item.exception.InvalidPriceException;
import hu.unideb.web.amazen.core.item.exception.ItemDeleteUnauthorizedException;
import hu.unideb.web.amazen.core.item.exception.ItemNotFoundException;
import hu.unideb.web.amazen.core.item.repository.ItemRepository;
import hu.unideb.web.amazen.core.item.service.ItemService;
import hu.unideb.web.amazen.core.security.JwtTokenProvider;
import hu.unideb.web.amazen.core.user.exception.UserDoesNotExistException;
import hu.unideb.web.amazen.core.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public ItemServiceImpl(
        ItemRepository itemRepository,
        UserRepository userRepository,
        JwtTokenProvider jwtTokenProvider) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public List<ItemDto> getItems() {
        return itemRepository.findAll().stream().map(Item::getItemDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ItemDto> getItem(Long id) {
        return itemRepository.findById(id).map(Item::getItemDto);
    }

    @Override
    public ItemDto createItem(HttpServletRequest request, ItemDto itemDto) {

        if (itemDto.getPrice().compareTo(new BigDecimal(0)) < 0) {
            throw new InvalidPriceException();
        }

        var user = userRepository.findUserByEmail(jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(request)))
            .orElseThrow(UserDoesNotExistException::new);

        var item = Item.getItemFromDto(itemDto);
        item.setOwner(user);

        return itemRepository.save(item).getItemDto();
    }

    @Override
    public void deleteItem(HttpServletRequest request, Long id) {

        var itemToDelete = checkItemOwner(request, id);

        itemRepository.delete(itemToDelete);
    }

    @Override
    public ItemDto updateItem(HttpServletRequest request, ItemDto itemDto) {

        var itemToUpdate = checkItemOwner(request, itemDto.getId());
        itemToUpdate.setDescription(itemDto.getDescription());
        itemToUpdate.setImageUrl(itemDto.getImageUrl());
        itemToUpdate.setName(itemDto.getName());
        itemToUpdate.setPrice(itemDto.getPrice());

        return itemRepository.save(itemToUpdate).getItemDto();
    }

    private Item checkItemOwner(HttpServletRequest request, Long id) {
        var user = userRepository.findUserByEmail(jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(request)))
            .orElseThrow(UserDoesNotExistException::new);

        var item = itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));

        if (!item.getOwner().getId().equals(user.getId())) {
            throw new ItemDeleteUnauthorizedException();
        }

        return item;
    }
}
