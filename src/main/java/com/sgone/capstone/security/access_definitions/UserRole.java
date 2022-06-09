package com.sgone.capstone.security.access_definitions;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.sgone.capstone.security.access_definitions.UserPermission.*;


// TODO: Add additional permissions to OWNER, ADMIN and USER as application functionalities develops
public enum UserRole {
    APP_OWNER (Sets.newHashSet(
            APP_OWNER_READ,
            APP_OWNER_WRITE,
            APP_ADMIN_READ,
            APP_ADMIN_WRITE,
            APP_USER_WRITE,
            APP_USER_READ
            // TODO: Add additional permissions here
    )
    ),
    APP_ADMIN (Sets.newHashSet(
            APP_ADMIN_READ,
            APP_ADMIN_WRITE,
            APP_USER_READ,
            APP_USER_WRITE
            // TODO: Add additional permissions here
    )
    ),
    APP_USER (Sets.newHashSet(
            APP_USER_READ,
            APP_USER_WRITE
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
}
