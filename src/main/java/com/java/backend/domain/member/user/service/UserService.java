package com.java.backend.domain.member.user.service;

import com.java.backend.domain.member.user.dto.UserRegisterRequestDto;
import com.java.backend.domain.member.user.entity.User;

public interface UserService {
    void registerOliveUser(UserRegisterRequestDto dto);
    String findPasswordByUserId(String userId);
    User login(String loginId, String passWord);
}
