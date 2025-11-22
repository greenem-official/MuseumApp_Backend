package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.*;
import org.daylight.museumbackend.model.User;
import org.daylight.museumbackend.repository.UserRepository;
import org.daylight.museumbackend.service.JwtService;
import org.daylight.museumbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if(userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiError("Пользователь уже существует", "USER_EXISTS"));
        }
        userService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            Optional<User> user = userService.findByUsername(userDetails.getUsername());
            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiError("Неверный логин или пароль", "BAD_CREDENTIALS"));
            }

            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(
                    token,
                    user.get().getUsername(),
                    user.get().getFullName(),
                    user.get().getRole()
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiError("Неверный логин или пароль", "BAD_CREDENTIALS"));
        }
    }

    @GetMapping("/check_token")
    public ResponseEntity<TokenCheckResponse> checkToken(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenCheckResponse(false, "Token invalid or expired"));
        }
        return ResponseEntity.ok(new TokenCheckResponse(true, "Token valid"));
    }
}
