package com.carrito.app.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "authority")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements Serializable {

    private static final long serialVersionUID = 7981109536262413941L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "authorities",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private List<User> users;

    public Authority(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
