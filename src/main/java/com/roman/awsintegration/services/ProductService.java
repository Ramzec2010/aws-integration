package com.roman.awsintegration.services;

import com.roman.awsintegration.rest.response.ProductResponse;
import com.roman.awsintegration.rest.request.ProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProduct(Long productId) throws Exception;

    void deleteProduct(Long productId);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long productId, ProductRequest request);
}
