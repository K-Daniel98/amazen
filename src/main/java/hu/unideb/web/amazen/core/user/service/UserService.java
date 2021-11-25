package hu.unideb.web.amazen.core.user.service;

import hu.unideb.web.amazen.core.user.dto.UserDto;
import hu.unideb.web.amazen.core.user.entity.User;

public interface UserService {

    void register(UserDto user);
    User login(UserDto user);

}
