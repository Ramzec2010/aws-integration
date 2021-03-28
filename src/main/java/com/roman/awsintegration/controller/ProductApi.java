package com.roman.awsintegration.controller;

import com.roman.awsintegration.rest.request.ProductRequest;
import com.roman.awsintegration.rest.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Product api", description = "Operation for product")
public interface ProductApi {


    @Operation(
            summary = "Delete product",
            description = "Delete product by given id"
    )
    @DeleteMapping(value = "/api/v1/product/{productId}",
            produces = {"application/json"})
    ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId", required = true)
                                       @Parameter(description = "Product id") @NotNull Long productId);

    @GetMapping(value = "/api/v1/product/{productId}",
            produces = {"application/json"})
    ResponseEntity<ProductResponse> getProduct(@Parameter(description = "Product id") @NotNull
                                               @PathVariable Long productId) throws Exception;

    @PostMapping(value = "/api/v1/product",
            produces = {"application/json"})
    ResponseEntity<ProductResponse> createProduct(@Parameter(description = "Product request") @RequestBody ProductRequest request);

    @PatchMapping(value = "/api/v1/product/{productId}",
            produces = {"application/json"})
    ResponseEntity<ProductResponse> updateProduct(@PathVariable @Parameter(description = "Product id") @NotNull Long productId,
                                                  @Parameter(description = "Product request") @RequestBody ProductRequest request);

    @GetMapping(value = "/api/v1/products",
            produces = {"application/json"})
    ResponseEntity<List<ProductResponse>> getAllProducts(@RequestParam(name = "minP", defaultValue = "0", required = false) BigDecimal minPrice,
                                                         @RequestParam(name = "maxP", defaultValue = Long.MAX_VALUE + "", required = false) BigDecimal maxPrice,
                                                         @RequestParam(name = "like", required = false, defaultValue = "") String like,
                                                         Pageable pageable);
}
