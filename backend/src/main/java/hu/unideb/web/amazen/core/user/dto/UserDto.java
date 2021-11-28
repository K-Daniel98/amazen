package hu.unideb.web.amazen.core.user.dto;

import hu.unideb.web.amazen.core.user.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String fullName;

    private String email;

    private String password;

    private String confirmPassword;

    private Role role;

}
