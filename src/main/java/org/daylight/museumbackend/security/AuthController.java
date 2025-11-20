package org.daylight.museumbackend.security;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.AuthResponse;
import org.daylight.museumbackend.dto.LoginRequest;
import org.daylight.museumbackend.dto.RegisterRequest;
import org.daylight.museumbackend.model.User;
import org.daylight.museumbackend.repository.UserRepository;
import org.daylight.museumbackend.service.JwtService;
import org.daylight.museumbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token, user.get().getUsername(), user.get().getFullName(), user.get().getRole()));
        }
        return ResponseEntity.badRequest().build();
    }
}
