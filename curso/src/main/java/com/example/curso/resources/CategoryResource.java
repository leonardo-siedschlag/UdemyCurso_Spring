package com.example.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entities.Category;
import com.example.curso.services.CategoryService;

//e recurso web que e implementado pelo um controlador rest//
@RestController
@RequestMapping(value="/categories")//para encontrar o caminho na web
public class CategoryResource {
	
	//injecao de dependencia
	@Autowired
	private CategoryService service;
	
	//ResponseEntity = tipo especifico do spring para retornar respostas de requisiçoes web
	@GetMapping//para indicar que esse metodo responde a requisicao do tipo get do http
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list  = service.findAll();//vem la da camada de serviço
		return ResponseEntity.ok().body(list);
		//retorna o corpo da reposta//
		
	}
	//value id = indicar que a requisição vai aceitar um id dentro da url
	@GetMapping(value= "/{id}")		//spring aceitar o id como parametro que vai chegar da url
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj  = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
}
