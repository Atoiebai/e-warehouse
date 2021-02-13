package net.sublime.warehouse.model.user;


import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Permission {

//    CAN_READ("can:read"),
    CAN_WRITE("can:write"),
//    CAN_POST("can:post"),
//    CAN_DELETE("can:delete"),
    CAN_MANAGE_USERS("can:manage:users")
//    CAN_MANAGE_ADMINS("can:manage:admins")
    ;

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }


}
