package com.jihu.entity;

import lombok.Data;

import java.util.List;

@Data
public class Permission {

    private Long permissionid;
    private String permission;
    private String description;
    private List<Role_permission> rp;
}
