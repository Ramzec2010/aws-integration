package com.roman.awsintegration.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private List<String> categories;
}
