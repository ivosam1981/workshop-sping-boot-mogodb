package com.ivosam.workshopmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivosam.workshopmongo.domain.Post;
import com.ivosam.workshopmongo.repositories.PostRepository;
import com.ivosam.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	
	public Post findById(String id) {
		Post post = repo.findById(id).orElse(null);
		if(post == null) {
			throw new ObjectNotFoundException("Objeto Não Encontrado");
		}
		return post;
	}
	
}
