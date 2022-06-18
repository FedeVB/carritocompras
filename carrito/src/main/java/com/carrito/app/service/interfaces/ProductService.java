package com.carrito.app.service.interfaces;

import com.carrito.app.domain.dto.ProductDto;
import com.carrito.app.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    List<ProductDto> findAllDto();

    Optional<Product> findById(Long id);

    Optional<ProductDto> findByIdDto(Long idProduct);

    Product save(Product product);

    boolean existsById(Long id);

    void deleteById(Long id);
}
