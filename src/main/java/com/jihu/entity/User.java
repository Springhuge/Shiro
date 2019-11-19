package com.jihu.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Long userid;
    private String username;
    private String password;
    private String locked;
    List<User_role> user_roles;
}
