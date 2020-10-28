package com.example.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curso.entities.User;
													 //tipo e  id			
public interface  UserRepository extends JpaRepository<User, Long>{

}
