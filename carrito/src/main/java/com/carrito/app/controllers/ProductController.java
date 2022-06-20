package com.carrito.app.controllers;

import com.carrito.app.domain.dto.ProductDto;
import com.carrito.app.domain.entity.Product;
import com.carrito.app.service.interfaces.ProductService;
import com.carrito.app.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<>(productService.findAllDto(), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(value = "id") Long id) {
        if (!productService.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        ProductDto product = productService.findByIdDto(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> created(@Valid @RequestBody Product product) {
        Product newProduct = productService.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Product> updated(@Valid @RequestBody Product product) {
        if (product.getId() == null) {
            throw new ProductNotFoundException("Product not found");
        }
        Product updateProduct = productService.save(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
