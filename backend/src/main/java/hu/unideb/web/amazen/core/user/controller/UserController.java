package hu.unideb.web.amazen.core.user.controller;

import hu.unideb.web.amazen.core.user.dto.LoginResponseDto;
import hu.unideb.web.amazen.core.user.dto.UserDto;
import hu.unideb.web.amazen.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private void register(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    private LoginResponseDto login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
