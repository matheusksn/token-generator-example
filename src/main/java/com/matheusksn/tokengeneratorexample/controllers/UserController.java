package com.matheusksn.tokengeneratorexample.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusksn.tokengeneratorexample.entities.RegistroAcesso;
import com.matheusksn.tokengeneratorexample.entities.User;
import com.matheusksn.tokengeneratorexample.repositories.RegistroAcessoRepository;
import com.matheusksn.tokengeneratorexample.repositories.UserRepository;
import com.matheusksn.tokengeneratorexample.services.TokenService;

@RestController
@RequestMapping("/api")
public class UserController {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final RegistroAcessoRepository registroAcessoRepository;

    @Autowired
    public UserController(TokenService tokenService,
                          UserRepository userRepository,
                          RegistroAcessoRepository registroAcessoRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.registroAcessoRepository = registroAcessoRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        if (!isValidCPF(user.getCpf())) {
            return "CPF inválido";
        }

        String token = tokenService.generateToken();

        registroAcesso(user, token);

        return "Token: " + token;
    }

    @PostMapping("/form")
    public String submitForm(@RequestParam String token) {
        if (!tokenService.isTokenValid(token)) {
            return "Token inválido ou expirado";
        }

        return "Formulário enviado com sucesso";
    }

    private boolean isValidCPF(String cpf) {

        return cpf != null && cpf.matches("\\d{11}");
    }

    private void registroAcesso(User user, String token) {

        RegistroAcesso registroAcesso = new RegistroAcesso(user, token, LocalDateTime.now());
        registroAcesso.setUser(user);
        registroAcesso.setToken(token);


        registroAcessoRepository.save(registroAcesso);
    }
}
