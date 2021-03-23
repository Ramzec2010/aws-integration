package com.roman.awsintegration.controller;

import com.roman.awsintegration.rest.response.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping(path = "/api/v1/categories/{categoryid}/products")
public interface CategoryProductApi {

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ProductResponse>> retrieveAllProducts(@RequestParam(name = "minP", defaultValue = "0", required = false) BigDecimal minPrice,
                                                              @RequestParam(name = "maxP", defaultValue = Long.MAX_VALUE+"", required = false) BigDecimal maxPrice,
                                                              @RequestParam(name = "like", defaultValue = "", required = false) String like,
            @PathVariable Long categoryid);

    @RequestMapping(path = "/{productid}", method = RequestMethod.POST)
    ResponseEntity<?> addProduct(@PathVariable(name = "categoryid") Long categoryId, @PathVariable Long productid);

    @RequestMapping(path = "/{productid}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeProduct(@PathVariable Long categoryid, @PathVariable Long productid);
}
