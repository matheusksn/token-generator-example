package com.matheusksn.tokengeneratorexample.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class RegistroAcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String token;

    private LocalDateTime dataHoraAcesso;

    public RegistroAcesso(User user, String token, LocalDateTime dataHoraAcesso) {
        this.user = user;
        this.token = token;
        this.dataHoraAcesso = dataHoraAcesso;
    }

	public LocalDateTime getDataHoraAcesso() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setToken(String token2) {
		// TODO Auto-generated method stub
		
	}

	public void setUser(User user2) {
		// TODO Auto-generated method stub
		
	}
}
