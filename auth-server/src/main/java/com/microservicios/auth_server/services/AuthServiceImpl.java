package com.microservicios.auth_server.services;

import com.microservicios.auth_server.dtos.TokenDto;
import com.microservicios.auth_server.dtos.UserDto;
import com.microservicios.auth_server.entities.UserEntity;
import com.microservicios.auth_server.helpers.JwtHelper;
import com.microservicios.auth_server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    private static final String USER_EXCEPTION_MSG = "Error to auth use";

    @Override
    public TokenDto login(UserDto user) {
        final var userFromDb = this.userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG));

        this.validPassword(user, userFromDb);
        return TokenDto.builder().accessToken(this.jwtHelper.createToken(userFromDb.getUsername())).build();
    }

    @Override
    public TokenDto validateToken(TokenDto token) {
        if(this.jwtHelper.validateToken(token.getAccessToken())){
            return TokenDto.builder().accessToken(token.getAccessToken()).build();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
    }

    private void validPassword(UserDto userDto, UserEntity userEntity) {
        if (!this.passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
        }
    }
}
