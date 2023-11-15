package com.diegosantos.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.diegosantos.workshopmongo.domain.Post;
import com.diegosantos.workshopmongo.domain.User;
import com.diegosantos.workshopmongo.dto.AuthorDTO;
import com.diegosantos.workshopmongo.repository.PostRepository;
import com.diegosantos.workshopmongo.repository.UserRepository;


@Configuration
public class instantiation implements CommandLineRunner{
	
	
	//--------------Banco de dados --------------
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//--------------------------------------------

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		//limpando
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User tiago = new User(null, "Tiago Santos", "tiago@gmail.com");
		User juliana = new User(null, "Juliana Iris", "juliana@gmail.com");
		User jessica = new User(null, "Jessica Libia", "jessica@gmail.com");
		userRepository.saveAll(Arrays.asList(tiago, juliana, jessica));
		
	
		
		//ADICIONANDO OS POSTS
		
		Post post1 = new Post(null,sdf.parse("21/03/2018") , "Partiu viagem!!", "Vou viajar para São Paulo. Abraços!",new AuthorDTO(tiago));
		Post post2 = new Post(null,sdf.parse("23/03/2018") , "Bom dia", "Acordei feliz hoje!", new AuthorDTO(tiago));
		
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		tiago.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(tiago);
		
	}

}
