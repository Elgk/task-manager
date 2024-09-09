package ru.skillbox.task.manager.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.skillbox.task.manager.exceptions.ContentNotFoundException;

import java.util.Arrays;

@RequiredArgsConstructor
public enum RoleEnum implements GrantedAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String roleValue;

    @Override
    public String getAuthority() {
        return roleValue;
    }

    public static RoleEnum of(String name) {
        return Arrays.stream(values()).filter(value -> value.roleValue.equals(name)).findFirst()
                .orElseThrow(() -> new ContentNotFoundException("Role not found"));
    }
}
