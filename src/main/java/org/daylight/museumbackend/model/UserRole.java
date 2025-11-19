package org.daylight.museumbackend.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    VISITOR("VISITOR"),
    ANONYMOUS("ANONYMOUS");

    private final String dbValue;

    UserRole(String dbValue) {
        this.dbValue = dbValue;
    }

    public static UserRole fromDbValue(String value) {
        for (UserRole r : values()) {
            if (r.dbValue.equals(value)) return r;
        }
        throw new IllegalArgumentException("Unknown role: " + value);
    }

    public static List<SimpleGrantedAuthority> toAuthorities(UserRole role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Converter(autoApply = true)
    public static class UserRoleConverter implements AttributeConverter<UserRole, String> {

        @Override
        public String convertToDatabaseColumn(UserRole role) {
            return role.getDbValue();
        }

        @Override
        public UserRole convertToEntityAttribute(String dbData) {
            return UserRole.fromDbValue(dbData);
        }
    }
}
