package com.roman.awsintegration.services.impl;

import com.roman.awsintegration.model.ProductEntity;
import com.roman.awsintegration.repository.ProductRepository;
import com.roman.awsintegration.rest.response.ProductResponse;
import com.roman.awsintegration.rest.request.ProductRequest;
import com.roman.awsintegration.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductEntity entity = ProductEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        ProductEntity save = productRepository.save(entity);
        return ProductResponse.builder()
                .id(save.getProductId())
                .price(save.getPrice())
                .name(save.getName())
                .build();
    }

    @Override
    public ProductResponse getProduct(Long productId) throws Exception {
        Optional<ProductEntity> byId = productRepository.findById(productId);
        return byId.map(entity -> ProductResponse.builder()
                    .id(entity.getProductId())
                    .name(entity.getName())
                    .price(entity.getPrice())
                    .build())
                .orElseThrow(Exception::new);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponse> getAllProducts(Pageable pageable) {
       return productRepository.findAll(pageable).stream().map(entity -> ProductResponse.builder()
                .id(entity.getProductId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build()).collect(Collectors.toList());
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
            ProductEntity save = productRepository.save(entity);
            return ProductResponse.builder()
                    .id(save.getProductId())
                    .name(save.getName())
                    .price(save.getPrice())
                    .build();
        }
        throw new IllegalArgumentException("Entity with given id does not exist");
    }
}
