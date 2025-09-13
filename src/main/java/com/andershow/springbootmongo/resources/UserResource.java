package com.andershow.springbootmongo.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andershow.springbootmongo.DTO.UserDTO;
import com.andershow.springbootmongo.domain.User;
import com.andershow.springbootmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		//o responseentity é um objeto sofisticado do jpa que retorna a requisição já com formato especial e com códigos de erro e tudo mais

		//List<User> list = service.findAll(); 
		//return ResponseEntity.ok().body(list); //retorna o response entity, se estiver ok no corpo da requisição ele retorna o list
	//comentei as linhas acima pois tivemos que refatora-las conforme o curso, deixei apenas pra saber como era a original
		//refatorei usando o método do DTO
		List<User> list = service.findAll(); //precisaremos transformar os itens do list, que são User em UserDTO
		//transformamos um list em stream, atribuimos os valores para UserDTO e transformamos novamente em List
		List<UserDTO> listDto = list.stream().map(x ->new UserDTO(x)).collect(Collectors.toList());
		//resumindo a linha acima, list.stream transforma a lista em stream, no map, pra cada item x na lista
		//instancia um novo UserDTO com x como argumento, o collect transforma novamente em list
		return ResponseEntity.ok().body(listDto);
		//RESUMINDO, 
		//refatorei para pegar os USERS e jogar numa lista, depois transformamos essa lista em UserDTO e a retornamos
		
	}
}
