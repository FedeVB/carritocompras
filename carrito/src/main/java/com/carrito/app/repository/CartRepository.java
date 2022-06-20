package com.carrito.app.repository;

import com.carrito.app.domain.dto.ITotalDto;
import com.carrito.app.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select SUM(pro.price) AS total,(SELECT COUNT(pro.id) FROM product AS pro INNER JOIN carts_products AS cp ON pro.id = cp.id_product\n" +
            "WHERE cp.id_cart = :idCart) AS cantidad from cart as ca Inner Join carts_products as cp on ca.id = cp.id_cart \n" +
            "Inner join product as pro ON cp.id_product = pro.id WHERE ca.id = :idCart", nativeQuery = true)
    ITotalDto getTotal(Long idCart);

    @Query(value = "DELETE FROM cart WHERE cart.id = :idCart",nativeQuery = true)
    void borrando(Long idCart);
}
