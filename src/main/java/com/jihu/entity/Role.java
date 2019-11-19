package com.jihu.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {

    private Long roleid;
    private String role;
    private String description;
    private List<User_role> user_roles;
    private List<Role_permission> role_permissions;
}
