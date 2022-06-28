package com.sgone.capstone.security.access_definitions;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sgone.capstone.security.access_definitions.UserPermission.*;

/**
 * This class defines all the roles available for this project. <br>
 * Each role has a set of permissions that determines what endpoints this role can access.
 * <br><br>
 * <i>APP_OWNER</i> - Role with the highest level of access, can read and alter information of all
 * other roles and self.
 * <br><br>
 * <i>APP_ADMIN</i> - This role can only access and write information about self and USER level roles.
 * <br><br>
 * <i>APP_USER</i> - Every user that signs up to use the website will be given this role by default.
 * Can only access alter information about self.
 * <br><br>
 * The <i>getGrantedAuthorities</i> method loops through a particular role's permission set and creates
 * a new set of <i>SimpleGrantedAuthority</i> using the <i>String</i> permission associated with
 * each permission enum constant.
 */


// TODO: Add additional permissions to OWNER, ADMIN and USER as application functionalities develops
public enum UserRole {
    APP_OWNER(Sets.newHashSet(
            APP_OWNER_READ_SELF,
            APP_OWNER_WRITE_SELF,
            APP_ADMIN_READ_ALL,
            APP_ADMIN_WRITE_ALL,
            APP_USER_READ_ALL,
            APP_USER_WRITE_ALL
            // TODO: Add additional permissions here
    )),
    APP_ADMIN(Sets.newHashSet(
            APP_ADMIN_READ_SELF,
            APP_ADMIN_WRITE_SELF,
            APP_USER_READ_ALL,
            APP_USER_WRITE_ALL
            // TODO: Add additional permissions here
    )),
    APP_USER(Sets.newHashSet(
            APP_USER_READ_SELF,
            APP_USER_WRITE_SELF
            // TODO: Add additional permissions here
    ));

    // Permissions must be unique
    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        return getPermissions()
                    .stream()
                    .map(permission ->
                            new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toSet());
    }
}

