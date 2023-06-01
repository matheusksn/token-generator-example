package com.matheusksn.tokengeneratorexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusksn.tokengeneratorexample.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
}
