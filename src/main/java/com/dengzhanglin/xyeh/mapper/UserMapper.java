package com.dengzhanglin.xyeh.mapper;

import com.dengzhanglin.xyeh.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserEntity findByEmail(String email);
    Long getIdByEmail(String email);
}
