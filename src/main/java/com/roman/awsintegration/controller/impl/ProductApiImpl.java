package com.roman.awsintegration.controller.impl;

import com.roman.awsintegration.controller.ProductApi;
import com.roman.awsintegration.rest.response.ProductResponse;
import com.roman.awsintegration.rest.request.ProductRequest;
import com.roman.awsintegration.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductApiImpl implements ProductApi {
    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(Long productId) throws Exception {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(Long productId, ProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(productId, request);
        return ResponseEntity.ok(productResponse);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
