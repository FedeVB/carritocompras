package com.carrito.app.repository;

import com.carrito.app.domain.dto.ProductDto;
import com.carrito.app.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT new com.carrito.app.domain.dto.ProductDto(p.id,p.title,p.description,p.price) FROM Product p")
    List<ProductDto> findAllDto();

    @Query(value = "SELECT new com.carrito.app.domain.dto.ProductDto(p.id,p.title,p.description,p.price) From Product p WHERE p.id = :idProduct")
    Optional<ProductDto> findByIdDto(Long idProduct);
}
