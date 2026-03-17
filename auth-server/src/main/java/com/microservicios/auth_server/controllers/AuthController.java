package com.microservicios.auth_server.controllers;

import com.microservicios.auth_server.dtos.TokenDto;
import com.microservicios.auth_server.dtos.UserDto;
import com.microservicios.auth_server.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "login")
    public ResponseEntity<TokenDto> jwtCreate(@RequestBody UserDto user) {
        return ResponseEntity.ok(this.authService.login(user));
    }

    @PostMapping(path = "validate")
    public ResponseEntity<TokenDto> jwtValidate(@RequestHeader String accessToken) {
        return ResponseEntity.ok(this.authService.validateToken(TokenDto.builder().accessToken(accessToken).build()));
    }
}
