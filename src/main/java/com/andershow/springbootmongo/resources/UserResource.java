package com.andershow.springbootmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andershow.springbootmongo.domain.User;
import com.andershow.springbootmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		//o responseentity é um objeto sofisticado do jpa que retorna a requisição já com formato especial e com códigos de erro e tudo mais

		List<User> list = service.findAll(); 
		return ResponseEntity.ok().body(list); //retorna o response entity, se estiver ok no corpo da requisição ele retorna o list
	}
}
