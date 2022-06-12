package com.sgone.capstone.security.access_definitions;

import com.google.common.collect.Sets;
import net.bytebuddy.build.Plugin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sgone.capstone.security.access_definitions.UserPermission.*;


// TODO: Add additional permissions to OWNER, ADMIN and USER as application functionalities develops
public enum UserRole {
    APP_OWNER (Sets.newHashSet(
            APP_OWNER_READ_SELF,
            APP_OWNER_WRITE_SELF,
            APP_ADMIN_READ_ALL,
            APP_ADMIN_WRITE_ALL,
            APP_USER_READ_ALL,
            APP_USER_WRITE_ALL
            // TODO: Add additional permissions here
    )
    ),
    APP_ADMIN (Sets.newHashSet(
            APP_ADMIN_READ_SELF,
            APP_ADMIN_WRITE_SELF,
            APP_USER_READ_ALL,
            APP_USER_WRITE_ALL
            // TODO: Add additional permissions here
    )
    ),
    APP_USER (Sets.newHashSet(
            APP_USER_READ_SELF,
            APP_USER_WRITE_SELF
            // TODO: Add additional permissions here
    )
    );

    // Permissions must be unique
    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
