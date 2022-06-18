package com.carrito.app.utils;

import com.carrito.app.domain.dto.ITotalDto;
import com.carrito.app.domain.entity.Cart;

public class CartUtil {

    public static Double getTotalPrice(ITotalDto totalDto, Cart cart) {
        double total = totalDto.getTotal();
        if (totalDto.getCantidad() > 5) {
            total -= (totalDto.getTotal() * 10) / 100;
        }
        if (totalDto.getCantidad() > 10) {
            switch (cart.getStatus().toString()) {
                case "COMUN":
                    total -= 200;
                    break;
                case "PROM":
                    total -= 500;
                    break;
                case "VIP":
                    total -= 700;
                    break;
            }
        }
        System.out.println(cart.getStatus().toString());
        return total;
    }
}
