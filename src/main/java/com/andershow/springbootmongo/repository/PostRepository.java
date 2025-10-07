package com.andershow.springbootmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.andershow.springbootmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	//a classe mongorepository nos da acesso a várias opções de manipulação de dados dentro do banco
	//ela exige que passemos como parâmetro a classe a ser manipulada bem como o tipo de dado do id do objeto
	
	
	//Query methods são métodos de busca do spring que PELO NOME DO MÉTODO
	//é capaz de puxar o método pronto, exemplo: findByName --> vai procurar pelo nome
	//facilita, pois não é preciso especificar o método, só usar o nome certo
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	//somente a declaração faz com que o spring monte a consulta
	
	
	
	//abaixo usamos um @query, consulta personalizada
	//https://www.mongodb.com/pt-br/docs/manual/reference/operator/query/regex/ 
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	@Query("{ $and: [{date: {$gte: 	?1}},{date: {$lte: 	?2}},{$or: [{'title': { $regex: ?0, $options: 'i' }},{'body': { $regex: ?0, $options: 'i' }}, {'coments.text': { $regex: ?0, $options: 'i' }} ]}]}")
	List<Post> fullSearch(String text,Date mindate, Date maxdate);
}
