package com.roman.awsintegration.rest.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {
    private String name;
    private String description;
}
