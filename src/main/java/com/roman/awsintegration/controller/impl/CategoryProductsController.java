package com.roman.awsintegration.controller.impl;

import com.roman.awsintegration.services.CategoryService;
import com.roman.awsintegration.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories/{categoryid}/products")
public class CategoryProductsController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


}

