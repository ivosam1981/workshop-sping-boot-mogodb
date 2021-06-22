package com.ivosam.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ivosam.workshopmongo.domain.Post;
import com.ivosam.workshopmongo.domain.User;
import com.ivosam.workshopmongo.dto.AuthorDTO;
import com.ivosam.workshopmongo.repositories.PostRepository;
import com.ivosam.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.saveAll(Arrays.asList(maria, alex,bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2021"), "partiu Viagem", "Vou viajar para Saão Paulo.", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2021"), "Bom dia", "Acordei", new AuthorDTO(maria) );

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
