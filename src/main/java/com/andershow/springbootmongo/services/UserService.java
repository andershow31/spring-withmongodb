package com.andershow.springbootmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andershow.springbootmongo.DTO.UserDTO;
import com.andershow.springbootmongo.domain.User;
import com.andershow.springbootmongo.repository.UserRepository;
import com.andershow.springbootmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	//os metodos na classe de serviço virão da interface repository, neste caso do UserRepository
	//A classe Serviço poderá ser injetada em outras classes, como no caso os controladores
	
	@Autowired //esse é o mecanismo de dependencias automáticas do spring
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	public Optional<User> findById(String id) {
		Optional<User> user= repo.findById(id);
		if(user == null) {
			throw new ObjectNotFoundException("Id não encontrado");
		}
		return user;
	}
	public User insert(User obj) {
		return repo.insert(obj);
	}
	public User fromDTO(UserDTO userDTO) {//este método converte DTO para user
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
