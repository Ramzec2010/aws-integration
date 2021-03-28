package com.roman.awsintegration.services.impl;

import com.roman.awsintegration.converter.Converter;
import com.roman.awsintegration.exception.ProductNotFoundException;
import com.roman.awsintegration.model.ProductEntity;
import com.roman.awsintegration.repository.ProductRepository;
import com.roman.awsintegration.rest.request.ProductRequest;
import com.roman.awsintegration.rest.response.ProductResponse;
import com.roman.awsintegration.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Converter converter;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductEntity entity = ProductEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        ProductEntity save = productRepository.save(entity);
        return converter.mapProductEntityToDto(save);
    }

    @Override
    public ProductResponse getProduct(Long productId) throws Exception {
        ProductEntity product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return converter.mapProductEntityToDto(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponse> getAllProducts(BigDecimal minVal, BigDecimal maxVal, String like, Pageable pageable) {
       return productRepository.findByPriceBetweenAndNameContaining(minVal, maxVal, like, pageable).stream()
               .map(entity -> converter.mapProductEntityToDto(entity))
               .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        Optional<ProductEntity> byId = productRepository.findById(productId);
        if (byId.isPresent()) {
            ProductEntity entity = byId.get();
            if (StringUtils.hasText(request.getName())) {
                entity.setName(request.getName());
            }
            if (Objects.nonNull(request.getPrice())) {
                entity.setPrice(request.getPrice());
            }
            ProductEntity product = productRepository.save(entity);
            return converter.mapProductEntityToDto(product);
        }
        throw new IllegalArgumentException("Entity with given id does not exist");
    }
}
