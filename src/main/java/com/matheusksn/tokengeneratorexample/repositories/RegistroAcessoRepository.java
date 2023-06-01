package com.matheusksn.tokengeneratorexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusksn.tokengeneratorexample.entities.RegistroAcesso;
import com.matheusksn.tokengeneratorexample.entities.User;

public interface RegistroAcessoRepository extends JpaRepository<RegistroAcesso, Long> {

	RegistroAcesso findByUserAndToken(User user, String token);
}
