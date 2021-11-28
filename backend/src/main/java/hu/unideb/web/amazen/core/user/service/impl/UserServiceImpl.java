package hu.unideb.web.amazen.core.user.service.impl;

import hu.unideb.web.amazen.core.security.JwtTokenProvider;
import hu.unideb.web.amazen.core.user.dto.LoginResponseDto;
import hu.unideb.web.amazen.core.user.dto.UserDto;
import hu.unideb.web.amazen.core.user.entity.Role;
import hu.unideb.web.amazen.core.user.entity.User;
import hu.unideb.web.amazen.core.user.exception.InvalidCredentialsException;
import hu.unideb.web.amazen.core.user.exception.UserAlreadyExistsException;
import hu.unideb.web.amazen.core.user.repository.UserRepository;
import hu.unideb.web.amazen.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtTokenProvider jwtTokenProvider,
        AuthenticationManager authenticationManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        user.setRole(Role.SELLER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(User.getUserFromDto(user));
    }

    @Override
    public LoginResponseDto login(UserDto userDto) {
        var result = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        return LoginResponseDto.builder()
            .token(jwtTokenProvider.createToken(userDto.getEmail(),(List<Role>)  result.getAuthorities()))
            .build();
    }

}
