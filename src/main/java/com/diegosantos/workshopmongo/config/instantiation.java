package com.diegosantos.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.diegosantos.workshopmongo.domain.User;
import com.diegosantos.workshopmongo.repository.UserRepository;


@Configuration
public class instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User tiago = new User(null, "Tiago Santos", "tiago@gmail.com");
		User juliana = new User(null, "Juliana Iris", "juliana@gmail.com");
		User jessica = new User(null, "Jessica Libia", "jessica@gmail.com");
		
		
		userRepository.saveAll(Arrays.asList(tiago, juliana, jessica));
		
		
		
		
		
		
		
	}

}
