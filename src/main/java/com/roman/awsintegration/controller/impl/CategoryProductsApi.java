package com.roman.awsintegration.controller.impl;

import com.roman.awsintegration.controller.CategoryProductApi;
import com.roman.awsintegration.rest.response.CategoryResponse;
import com.roman.awsintegration.rest.response.ProductResponse;
import com.roman.awsintegration.services.CategoryProductService;
import com.roman.awsintegration.services.CategoryService;
import com.roman.awsintegration.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryProductsApi  implements CategoryProductApi {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryProductService categoryProductService;

    @Override
    public ResponseEntity<List<ProductResponse>> retrieveAllProducts(Long categoryid) {
        CategoryResponse category = categoryService.getCategory(categoryid);
        return ResponseEntity.ok(category.getProducts());
    }

    @Override
    public ResponseEntity<?> addProduct(Long categoryid, Long productid) {
        categoryProductService.addProductToCategory(categoryid,productid);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> removeProduct(Long categoryid, Long productid) {
        categoryProductService.removeProductFromCategory(categoryid, productid);
        return ResponseEntity.noContent().build();
    }

}

