package com.roman.awsintegration.services;

public interface CategoryProductService {

    void addProductToCategory(Long categoryId, Long productId);

    void removeProductFromCategory(Long categoryid, Long productid);
}
