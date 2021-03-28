package com.roman.awsintegration.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Category request")
public class CategoryRequest {

    @Schema(description = "Category name", example = "Food")
    private String name;
    @Schema(description = "Category description", example = "Time to eat")
    private String description;
}
