package com.roman.awsintegration.rest.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<String> categories;
}
