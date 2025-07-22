package com.andershow.springbootmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andershow.springbootmongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		//o responseentity é um objeto sofisticado do jpa que retorna a requisição já com formato especial e com códigos de erro e tudo mais
		User maria = new User("1", "maria", "maria@cu");
		User joao = new User("2", "joao", "joao@cu");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, joao));
		return ResponseEntity.ok().body(list); //retorna o response entity, se estiver ok no corpo da requisição ele retorna o list
	}
}
