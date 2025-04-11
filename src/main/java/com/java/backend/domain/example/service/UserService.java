package com.java.backend.domain.example.service;

import com.java.backend.domain.example.dto.OliveUserRegisterRequestDto;
import com.java.backend.domain.user.entity.User;

public interface UserService {
    void registerOliveUser(OliveUserRegisterRequestDto dto);
    String findPasswordByUserId(String userId);
    User login(String loginId, String passWord);
}
