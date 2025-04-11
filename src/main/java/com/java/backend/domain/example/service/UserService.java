package com.java.backend.domain.example.service;

import com.java.backend.domain.example.dto.UserRegisterRequestDto;
import com.java.backend.domain.user.entity.User;

public interface UserService {
    void registerOliveUser(UserRegisterRequestDto dto);
    String findPasswordByUserId(String userId);
    User login(String loginId, String passWord);
}
