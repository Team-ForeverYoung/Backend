package com.domain.product.service;

import com.domain.product.dto.ProductRequest;
import com.domain.product.dto.ProductResponse;
import com.domain.product.entity.Product;
import com.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse saveProduct(ProductRequest request) {
        Product product = Product.builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .image(request.getImage())
                .build();

        Product saved = productRepository.save(product);

        return ProductResponse.builder()
                .productId(saved.getProductId())
                .productName(saved.getProductName())
                .price(saved.getPrice())
                .image(saved.getImage())
                .build();
    }

    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .image(product.getImage())
                .build();
    }
}
