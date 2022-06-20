package com.carrito.app.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE users SET activated = false WHERE id = :idUser")
public class User implements Serializable {

    private static final long serialVersionUID = -1384475977812790539L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    @NotBlank
    private String password;
    @NotBlank
    @Email
    @Column(unique = true,nullable = false)
    private String email;
    private boolean activated;
    private boolean vip;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user")
    private Cart cart;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "id_authority", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_authority"})})
    @JsonIgnoreProperties(value = "users")
    private List<Authority> authorities;

    {
        this.authorities=new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.activated=true;
        this.vip=false;
    }
}
