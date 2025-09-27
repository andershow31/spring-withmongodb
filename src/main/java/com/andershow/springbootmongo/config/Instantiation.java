package com.andershow.springbootmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andershow.springbootmongo.DTO.AuthorDTO;
import com.andershow.springbootmongo.domain.Post;
import com.andershow.springbootmongo.domain.User;
import com.andershow.springbootmongo.repository.PostRepository;
import com.andershow.springbootmongo.repository.UserRepository;


//carga inicial do banco de dados para testes, já inicia o banco com alguns dados


@Configuration
public class Instantiation implements CommandLineRunner{
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		//o código,as duas linhas, acima define o formato da data, considerando o horário de greenwich
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		//o sfd.parse cria o date
		Post post1 = new Post(null, sdf.parse("12/02/2025"), "Partiu Viajar", "Partindo de viagem hoje", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("10/03/2025"), "Olá galera", "Este é meu segundo post aqui na rede", new AuthorDTO(maria));
		postRepository.saveAll(Arrays.asList(post1,post2));		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
