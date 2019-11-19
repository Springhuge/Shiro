package com.jihu.service;


import com.jihu.entity.User;

import java.util.Set;

public interface UserService {
    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
