package com.andershow.springbootmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andershow.springbootmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	//a classe mongorepository nos da acesso a várias opções de manipulação de dados dentro do banco
	//ela exige que passemos como parâmetro a classe a ser manipulada bem como o tipo de dado do id do objeto
	
}
