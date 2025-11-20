package org.daylight.museumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.daylight.museumbackend.model.UserRole;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private String fullName;
    private UserRole role;
}
