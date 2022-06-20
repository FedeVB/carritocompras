package com.carrito.app.domain.entity;

import com.carrito.app.domain.enumerations.CartStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = 5730018947089963623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @ManyToMany(cascade =

            CascadeType.MERGE)
    @JoinTable(name = "carts_products", joinColumns = @JoinColumn(name = "id_cart", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_cart", "id_product"})})
    @JsonIgnoreProperties(value = "carts")
    private Set<Product> products;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user",referencedColumnName = "id")
    @JsonIgnoreProperties(value = "cart")
    @JsonIgnore
    private User user;

    public Cart(CartStatus status) {
        this.status = status;
    }

    @PrePersist
    public void prePersist(){
        this.date=LocalDate.now();
    }

    @PreRemove
    public void preRemove(){
        this.getUser().setCart(null);
        this.setProducts(new HashSet<>());
    }
}
