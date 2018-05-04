package com.dengzhanglin.xyeh.repository;

import com.dengzhanglin.xyeh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {
    private UserMapper userMapper;

    @Autowired
    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Boolean isExistByEmail(String email) {
        return this.userMapper.getIdByEmail(email) > 0;
    }
}
