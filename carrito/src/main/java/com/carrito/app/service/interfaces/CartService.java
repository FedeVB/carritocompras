package com.carrito.app.service.interfaces;

import com.carrito.app.domain.dto.ITotalDto;
import com.carrito.app.domain.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> findAll();

    Optional<Cart> findById(Long id);

    Cart save(Cart cart);

    boolean existsById(Long id);

    void deleteById(Long id);

    ITotalDto getTotal(Long idCart);
}
