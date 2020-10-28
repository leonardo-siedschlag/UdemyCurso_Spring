package com.example.curso.resources;

import java.net.URI;
import java.util.List;

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

import com.example.curso.entities.User;
import com.example.curso.services.UserService;

//e recurso web que e implementado pelo um controlador rest//
@RestController
@RequestMapping(value="/users")//para encontrar o caminho na web
public class UserResource {
	
	//injecao de dependencia
	@Autowired
	private UserService service;
	
	//ResponseEntity = tipo especifico do spring para retornar respostas de requisiçoes web
	@GetMapping//para indicar que esse metodo responde a requisicao do tipo get do http
	public ResponseEntity<List<User>> findAll(){
		List<User> list  = service.findAll();//vem la da camada de serviço
		return ResponseEntity.ok().body(list);
		//retorna o corpo da reposta//
		
	}
	//value id = indicar que a requisição vai aceitar um id dentro da url
	@GetMapping(value= "/{id}")		//spring aceitar o id como parametro que vai chegar da url
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj  = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	@PostMapping //ira receber metodo post do http, que  objeto user seja de deserializado, pois esta formato json
	public ResponseEntity<User> insert (@RequestBody User obj){
		obj  = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();//ele espera 201 e nao 200
		return ResponseEntity.created(uri).body(obj);
	}
	@DeleteMapping(value= "/{id}")	
	public ResponseEntity<Void> delete(@PathVariable Long id){//PathVariable para long id seja reconhecida como variavel da url
		service.deleted(id);
		return ResponseEntity.noContent().build();//noContent = retorna uma resposta vazia com codigo http 204
			
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
	    obj  = service.update(id, obj);
	return ResponseEntity.ok().body(obj);
	}
	
	
	
}
