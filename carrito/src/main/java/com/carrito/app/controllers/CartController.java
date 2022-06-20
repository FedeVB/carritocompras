package com.carrito.app.controllers;

import com.carrito.app.domain.dto.ITotalDto;
import com.carrito.app.domain.entity.Cart;
import com.carrito.app.domain.entity.Product;
import com.carrito.app.domain.entity.User;
import com.carrito.app.repository.CartRepository;
import com.carrito.app.service.interfaces.CartService;
import com.carrito.app.service.interfaces.ProductService;
import com.carrito.app.service.interfaces.UserService;
import com.carrito.app.exceptions.CartNotFoundException;
import com.carrito.app.exceptions.ProductNotFoundException;
import com.carrito.app.exceptions.UserNotFoundException;
import com.carrito.app.utils.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin(value = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    public CartController(CartService cartService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Cart> findById(@PathVariable(value = "id") Long id) {
        Cart cart = cartService.findById(id).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/idUser/{idUser}")
    public ResponseEntity<Cart> created(@PathVariable(value = "idUser") Long idUser, @Valid @RequestBody Cart cart) {
        if (!userService.existsById(idUser)) {
            throw new UserNotFoundException("User not found");
        }
        if (cart.getStatus() == null) {
            throw new CartNotFoundException("Cart not found , status not defined");
        }
        User user = userService.findById(idUser).get();
        user.setCart(cart);
        cart.setUser(user);
        Cart newCart = cartService.save(cart);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    @PutMapping(value = "/idUser/{idUser}")
    public ResponseEntity<Cart> updated(@PathVariable(value = "idUser") Long idUser, @Valid @RequestBody Cart cart) {
        if (!userService.existsById(idUser)) {
            throw new UserNotFoundException("User not found");
        }
        if (cart.getId() == null) {
            throw new CartNotFoundException("Cart not found");
        }
        Cart updateCart = cartService.save(cart);
        return new ResponseEntity<>(updateCart, HttpStatus.OK);
    }

    @PutMapping(value = "/addProduct/{idCart}/idProduct/{idProduct}")
    public ResponseEntity<Cart> addProduct(@PathVariable(value = "idCart") Long idCart, @PathVariable(value = "idProduct") Long idProduct) {
        if (!cartService.existsById(idCart)) {
            throw new CartNotFoundException("Cart not found");
        }
        if (!productService.existsById(idProduct)) {
            throw new ProductNotFoundException("Product not found");
        }
        Cart cart = cartService.findById(idCart).get();
        Product product = productService.findById(idProduct).get();
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        cart = cartService.save(cart);
        //Volvemos a calcular el precio
        ITotalDto totalDto = cartService.getTotal(idCart);
        cart.setTotalPrice(CartUtil.getTotalPrice(totalDto, cart));
        cart = cartService.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping(value = "/takeProduct/{idCart}/idProduct/{idProduct}")
    public ResponseEntity<Cart> takeProduct(@PathVariable(value = "idCart") Long idCart, @PathVariable(value = "idProduct") Long idProduct) {
        if (!cartService.existsById(idCart)) {
            throw new CartNotFoundException("Cart not found");
        }
        if (!productService.existsById(idProduct)) {
            throw new ProductNotFoundException("Product not found");
        }
        Cart cart = cartService.findById(idCart).get();
        Product product = productService.findById(idProduct).get();
        cart.getProducts().removeIf(product1 -> {
            return Objects.equals(product1.getId(), idProduct);
        });
        product.getCarts().removeIf(cart1 -> {
            return Objects.equals(cart1.getId(), idCart);
        });
        cart = cartService.save(cart);
        //Volvemos a calcular el precio
        ITotalDto totalDto = cartService.getTotal(idCart);
        cart.setTotalPrice(CartUtil.getTotalPrice(totalDto, cart));
        cart = cartService.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        cartService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
