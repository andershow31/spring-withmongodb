package com.andershow.springbootmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andershow.springbootmongo.domain.User;
import com.andershow.springbootmongo.repository.UserRepository;


//carga inicial do banco de dados para testes, já inicia o banco com alguns dados


@Configuration
public class Instantiation implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
	}

}
