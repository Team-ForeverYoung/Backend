package com.java.backend.domain.member.user.dto;

public class UserRegisterRequestDto {

    private String userId; // 사용자 입력 ID 필드 추가
    private String name;
    private String email;
    private String passWord;
    private Integer point;
    private String country;

    // getter 추가
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return passWord;
    }

    public Integer getPoint() {
        return point;
    }

    public String getCountry() {
        return country;
    }
}

