package com.andershow.springbootmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andershow.springbootmongo.domain.User;
import com.andershow.springbootmongo.repository.UserRepository;

@Service
public class UserService {
	//os metodos na classe de serviço virão da interface repository, neste caso do UserRepository
	//A classe Serviço poderá ser injetada em outras classes, como no caso os controladores
	
	@Autowired //esse é o mecanismo de dependencias automáticas do spring
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
}
