package com.java.backend.domain.member.point.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SavePointReqDto {
    private int price;
    private Long userId;

    @Override
    public String toString() {
        return "SavePointReqDto{" +
            "price=" + price +
            ", userId=" + userId +
            '}';
    }
}
