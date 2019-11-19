package com.jihu.mapper;

import com.jihu.entity.User;

public interface UserMapper {
    User selectByUsername(String username);
}
