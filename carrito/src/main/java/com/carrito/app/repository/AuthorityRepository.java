package com.carrito.app.repository;

import com.carrito.app.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    List<Authority> findByUsersId(Long idUser);
}
