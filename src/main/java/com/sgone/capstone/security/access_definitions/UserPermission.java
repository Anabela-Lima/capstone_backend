package com.sgone.capstone.security.access_definitions;

public enum UserPermission {
    // Permission to Read and Edit OWNER level account information
    APP_OWNER_READ("app_owner:read"),
    APP_OWNER_WRITE("app_owner:write"),

    // Permission to Read and Edit ADMIN level account information
    APP_ADMIN_READ("app_admin:read"),
    APP_ADMIN_WRITE("app_admin:write"),

    // Permission to Read and Edit normal APP USER level account information
    APP_USER_READ("app_user:read"),
    APP_USER_WRITE("app_user:write");

    /*
        TODO: Add more permissions here depending on application functionalities
     */

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
