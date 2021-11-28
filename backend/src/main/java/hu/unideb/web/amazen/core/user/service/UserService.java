package hu.unideb.web.amazen.core.user.service;

import hu.unideb.web.amazen.core.user.dto.LoginResponseDto;
import hu.unideb.web.amazen.core.user.dto.UserDto;

public interface UserService {

    void register(UserDto user);
    LoginResponseDto login(UserDto user);

}
