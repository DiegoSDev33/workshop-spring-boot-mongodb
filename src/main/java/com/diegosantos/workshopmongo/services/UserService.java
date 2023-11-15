package com.diegosantos.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegosantos.workshopmongo.domain.User;
import com.diegosantos.workshopmongo.repository.UserRepository;

@Service // falar para o spring que essa classe pode ser injetavel em outras classes
public class UserService {
	
	@Autowired // busca automaticamente e instancia a classe que voce chamar abaixo dele
	private UserRepository repo;
	
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	
}
