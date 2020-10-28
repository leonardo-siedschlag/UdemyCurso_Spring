package com.example.curso.services.exceptions;

//para retornar um p

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		//super  = chama o construtor da super classe
		super("Resource not found Id "+ id);
		
	}
	
}
