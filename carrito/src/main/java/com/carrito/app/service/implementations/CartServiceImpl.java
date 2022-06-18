package com.carrito.app.service.implementations;

import com.carrito.app.domain.dto.ITotalDto;
import com.carrito.app.domain.entity.Cart;
import com.carrito.app.repository.CartRepository;
import com.carrito.app.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    @Transactional
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public boolean existsById(Long id) {
        return cartRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ITotalDto getTotal(Long idCart) {
        return cartRepository.getTotal(idCart);
    }
}
