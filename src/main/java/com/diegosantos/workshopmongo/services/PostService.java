package com.diegosantos.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegosantos.workshopmongo.domain.Post;
import com.diegosantos.workshopmongo.repository.PostRepository;
import com.diegosantos.workshopmongo.services.exception.ObjectNotFoundException;

@Service // falar para o spring que essa classe pode ser injetavel em outras classes
public class PostService {
	
	@Autowired // busca automaticamente e instancia a classe que voce chamar abaixo dele
	private PostRepository repo;
	
	public Post findById(String id) {
	    Optional<Post> obj = repo.findById(id);
	    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post>fullSearch(String text, Date minDate, Date maxDate){
	maxDate = new Date(maxDate.getTime() + 24 *60 *60 *1000);
	return repo.fullSearch(text, minDate, maxDate);
	
	
}
}