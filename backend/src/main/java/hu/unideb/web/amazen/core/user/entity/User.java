package hu.unideb.web.amazen.core.user.entity;

import hu.unideb.web.amazen.core.item.entity.Item;
import hu.unideb.web.amazen.core.order.entity.Order;
import hu.unideb.web.amazen.core.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Order> orders;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Item> items;

    public static User getUserFromDto(UserDto userDto) {
        return User.builder()
            .fullName(userDto.getFullName())
            .email(userDto.getEmail())
            .roles(List.of(userDto.getRole()))
            .password(userDto.getPassword())
            .build();
    }
}
