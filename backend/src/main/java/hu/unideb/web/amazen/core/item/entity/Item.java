package hu.unideb.web.amazen.core.item.entity;

import hu.unideb.web.amazen.core.item.dto.ItemDto;
import hu.unideb.web.amazen.core.order.entity.Order;
import hu.unideb.web.amazen.core.user.dto.UserDto;
import hu.unideb.web.amazen.core.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Order order;

    public static Item getItemFromDto(ItemDto itemDto) {
        return Item.builder()
            .name(itemDto.getName())
            .description(itemDto.getDescription())
            .imageUrl(itemDto.getImageUrl())
            .price(itemDto.getPrice())
            .build();
    }

    public ItemDto getItemDto() {
        return ItemDto.builder()
            .id(id)
            .owner(String.format("%s#%s", owner.getFullName(), owner.getId()))
            .name(name)
            .description(description)
            .price(price)
            .imageUrl(imageUrl)
            .build();
    }
}
