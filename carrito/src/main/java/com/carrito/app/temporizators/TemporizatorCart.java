package com.carrito.app.temporizators;

import com.carrito.app.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TemporizatorCart {

    private CartService cartService;

    @Autowired
    public TemporizatorCart(CartService cartService) {
        this.cartService = cartService;
    }

    @Scheduled(cron = "0 0 0 * * *",zone = "America/Argentina/Buenos_Aires")
    public void eliminarCarts(){
        //Aca se debe ejecutar la consulta que elimina todos los carts a las 0hs de cada dia
    }
}
