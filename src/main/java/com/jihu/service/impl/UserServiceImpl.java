package com.jihu.service.impl;

import com.jihu.entity.Role;
import com.jihu.entity.User;
import com.jihu.entity.User_role;
import com.jihu.mapper.RoleMapper;
import com.jihu.mapper.UserMapper;
import com.jihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return user;
    }

    @Override
    public Set<String> findRoles(String username) {
        Set<String> roles = new HashSet<>();
        User user = userMapper.selectByUsername(username);
        List<User_role> user_roles = user.getUser_roles();
        for (User_role user_role : user_roles) {
            Role role = user_role.getRole();
            roles.add(role.getRole());
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<>();
        User user = userMapper.selectByUsername(username);
        List<User_role> user_roles = user.getUser_roles();
        for (User_role user_role : user_roles) {
          Role role = roleMapper.selectRoleById(user_role.getRole().getRoleid());
        }
        return null;
    }
}
