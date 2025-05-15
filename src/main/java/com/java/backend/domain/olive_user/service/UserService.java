package com.java.backend.domain.olive_user.service;

import com.java.backend.domain.olive_user.dto.UserRegisterRequestDto;
import com.java.backend.domain.olive_user.entity.User;

public interface UserService {
    void registerOliveUser(UserRegisterRequestDto dto);
    String findPasswordByUserId(String userId);
    User login(String loginId, String passWord);
}
