package com.java.backend.domain.member.user.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.member.user.dto.UserRegisterRequestDto;
import com.java.backend.domain.member.user.entity.User;
import com.java.backend.domain.member.user.repository.OliveUserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final OliveUserRepository oliveUserRepository;

    public UserServiceImpl(OliveUserRepository oliveUserRepository) {
        this.oliveUserRepository = oliveUserRepository;
    }

    @Override
    public void registerOliveUser(UserRegisterRequestDto requestDto) {
        User newUser = createUserFromDto(requestDto);
        oliveUserRepository.save(newUser);
    }

    @Override
    public String findPasswordByUserId(String userId) {
        return oliveUserRepository.findPassWordByUserId(userId);
    }

    @Override
    public User login(String userId, String passWord) {
        User user = oliveUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if (!user.getPassWord().equals(passWord)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }



    private User createUserFromDto(UserRegisterRequestDto dto) {
        return User.builder()
                .userId(dto.getUserId()) // 사용자 ID 설정
                .name(dto.getName())
                .email(dto.getEmail())
                .passWord(dto.getPassWord())
                .point(dto.getPoint())
                .country(dto.getCountry())
                .build();
    }
}
