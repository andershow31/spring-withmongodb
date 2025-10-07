package com.andershow.springbootmongo.services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andershow.springbootmongo.domain.Post;
import com.andershow.springbootmongo.repository.PostRepository;
import com.andershow.springbootmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	//os metodos na classe de serviço virão da interface repository, neste caso do UserRepository
	//A classe Serviço poderá ser injetada em outras classes, como no caso os controladores
	
	@Autowired //esse é o mecanismo de dependencias automáticas do spring
	private PostRepository repo;
	
	public Post findById(String id) {
		Post post= repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));
		return post;
	}
	public List<Post> findByTitle(String title){
		return repo.searchTitle(title);
	}
	
	public List<Post> fullSearch(String text,Date mindate, Date maxdate){
		maxdate = new Date(maxdate.getTime() + 24*60*60*1000);//soma um dia em milisegundos para pegar os posts do dia atual + 1
		return repo.fullSearch(text, mindate, maxdate);
	}
}
