package com.java.backend.domain.point.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class point_Dto {
    private int price;
    private Long userId;

    @Override
    public String toString() {
        return "point_Dto{" +
            "price=" + price +
            ", userId=" + userId +
            '}';
    }
}
