package com.matheusksn.tokengeneratorexample.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusksn.tokengeneratorexample.entities.RegistroAcesso;
import com.matheusksn.tokengeneratorexample.entities.User;
import com.matheusksn.tokengeneratorexample.repositories.RegistroAcessoRepository;
import com.matheusksn.tokengeneratorexample.repositories.UserRepository;
import com.matheusksn.tokengeneratorexample.services.TokenService;

@RestController
@RequestMapping("/api/token")
public class TokenGeneratorController {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final RegistroAcessoRepository registroAcessoRepository;

    @Autowired
    public TokenGeneratorController(TokenService tokenService,
                                    UserRepository userRepository,
                                    RegistroAcessoRepository registroAcessoRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.registroAcessoRepository = registroAcessoRepository;
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<String> gerarToken(@PathVariable String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            String token = tokenService.generateToken();
            LocalDateTime dataHoraAcesso = LocalDateTime.now();
            RegistroAcesso registroAcesso = new RegistroAcesso(user, token, dataHoraAcesso);
            registroAcessoRepository.save(registroAcesso);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @PostMapping("/{cpf}/validate")
    public ResponseEntity<String> validarToken(@PathVariable String cpf, @RequestBody String token) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            RegistroAcesso registroAcesso = registroAcessoRepository.findByUserAndToken(user, token);
            if (registroAcesso != null && tokenService.isTokenValid(token)) {
                return ResponseEntity.ok("Token válido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
}
