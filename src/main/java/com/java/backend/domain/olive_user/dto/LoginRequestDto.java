package com.java.backend.domain.olive_user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequestDto {
    private String userId;
    private String passWord;

    // getter, setter
}
