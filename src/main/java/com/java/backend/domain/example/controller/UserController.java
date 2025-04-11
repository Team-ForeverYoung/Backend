package com.java.backend.domain.example.controller;

import com.java.backend.domain.example.dto.LoginRequestDto;
import com.java.backend.domain.user.entity.User;
import com.java.backend.global.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.backend.domain.example.dto.UserRegisterRequestDto;
import com.java.backend.domain.example.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/test")

public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    @PostMapping("/join")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestDto requestDto) {
        userService.registerOliveUser(requestDto);
        return ResponseEntity.ok("사용자 등록 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            User user = userService.login(request.getUserId(), request.getPassWord());

            String token = jwtTokenProvider.createToken(user.getUserId());

            // JSON 형태로 응답 (token 포함)
            Map<String, String> response = new HashMap<>();
            response.put("message", "로그인 성공!");
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "로그인 실패: " + e.getMessage()));
        }
    }


}
