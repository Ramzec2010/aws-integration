package com.roman.awsintegration.controller;

import com.roman.awsintegration.rest.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(path = "/api/v1/categories/{categoryid}/products")
public interface CategoryProductApi {

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ProductResponse>> retrieveAllProducts(@PathVariable Long categoryid);

    @RequestMapping(path = "/{productid}", method = RequestMethod.POST)
    ResponseEntity<?> addProduct(@PathVariable Long categoryid, @PathVariable Long productid);

    @RequestMapping(path = "/{productid}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeProduct(@PathVariable Long categoryid, @PathVariable Long productid);
}