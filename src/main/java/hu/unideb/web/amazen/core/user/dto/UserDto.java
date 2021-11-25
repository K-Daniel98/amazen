package hu.unideb.web.amazen.core.user.dto;

import hu.unideb.web.amazen.core.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private String username;

    private String password;

    private Set<Order> orders;

}
