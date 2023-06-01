package com.matheusksn.tokengeneratorexample.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime dataHoraExpiracao;
	public void setToken(String token2) {
		// TODO Auto-generated method stub
		
	}
	public void setDataHoraExpiracao(LocalDateTime dataHoraExpiracao2) {
		// TODO Auto-generated method stub
		
	}
	public LocalDateTime getDataHoraExpiracao() {
		// TODO Auto-generated method stub
		return null;
	}

}
