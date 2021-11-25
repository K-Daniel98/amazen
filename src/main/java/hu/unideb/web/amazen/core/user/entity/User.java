package hu.unideb.web.amazen.core.user.entity;

import hu.unideb.web.amazen.core.order.entity.Order;
import hu.unideb.web.amazen.core.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    @OneToMany
    private Set<Order> orders;

    public static User getUserFromDto(UserDto userDto) {
        return User.builder()
            .username(userDto.getUsername())
            .password(userDto.getPassword())
            .build();
    }
}
