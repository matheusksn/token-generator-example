package com.matheusksn.tokengeneratorexample.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    public User(String cpf) {
        this.cpf = cpf;
    }

	public String getCpf() {
		// TODO Auto-generated method stub
		return null;
	}
}
