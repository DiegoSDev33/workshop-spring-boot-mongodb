package com.diegosantos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegosantos.workshopmongo.domain.User;
import com.diegosantos.workshopmongo.dto.UserDTO;
import com.diegosantos.workshopmongo.repository.UserRepository;
import com.diegosantos.workshopmongo.services.exception.ObjectNotFoundException;

@Service // falar para o spring que essa classe pode ser injetavel em outras classes
public class UserService {
	
	@Autowired // busca automaticamente e instancia a classe que voce chamar abaixo dele
	private UserRepository repo;
	
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); // chamado o metodo de busca para validar se o id existe, caso nao ele ja manda a excessao 
		repo.deleteById(id);
	}
	
	
	// instanciado o DTO dentro de UserService para otimizar, pois o banco de dados esta vinculado a essa classe
	public User fromDTO(UserDTO objDto){
		return new User(objDto.getId(),objDto.getName(), objDto.getEmail());
		
	}
	
	
	
	
	
}
