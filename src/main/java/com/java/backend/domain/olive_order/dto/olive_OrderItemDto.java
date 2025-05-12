package com.java.backend.domain.olive_order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class olive_OrderItemDto {

    @JsonProperty("name")
    private String productName;

    private int salePrice;
}
