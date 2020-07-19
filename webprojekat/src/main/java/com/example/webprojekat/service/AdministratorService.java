package com.example.webprojekat.service;

import java.util.List;

import com.example.webprojekat.entity.Administrator;

public interface AdministratorService {
	//novo*
	Administrator find(String korisnocko, String lozinka);
	Administrator save(Administrator a);
	Administrator findOne(Long id);
	List<Administrator> findAll();
	void delete(Long id);
}
