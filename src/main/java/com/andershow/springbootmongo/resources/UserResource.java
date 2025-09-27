package com.andershow.springbootmongo.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andershow.springbootmongo.DTO.UserDTO;
import com.andershow.springbootmongo.domain.Post;
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
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
		User obj = service.fromDTO(userDTO);//essa parte transforma um userDTO em user
		obj = service.insert(obj); //aqui inserimos no banco
		//a parte abaixo é para retornar a url com o id, por exemplo: LOCALHOST:8080/USERS/12345
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();	
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id){
		User obj = service.fromDTO(userDTO);//essa parte transforma um userDTO em user
		obj.setId(id);
		obj = service.update(obj); 
		return ResponseEntity.noContent().build();	
	}
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findPost(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
