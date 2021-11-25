package hu.unideb.web.amazen.core.user.service.impl;

import hu.unideb.web.amazen.core.user.dto.CredentialsDto;
import hu.unideb.web.amazen.core.user.dto.UserDto;
import hu.unideb.web.amazen.core.user.entity.User;
import hu.unideb.web.amazen.core.user.exception.InvalidCredentialsException;
import hu.unideb.web.amazen.core.user.exception.UserAlreadyExistsException;
import hu.unideb.web.amazen.core.user.repository.UserRepository;
import hu.unideb.web.amazen.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(User.getUserFromDto(user));
    }

    @Override
    public User login(UserDto userDto) {
        var user = userRepository.findUserByUsername(userDto.getUsername())
            .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user;
    }

}
