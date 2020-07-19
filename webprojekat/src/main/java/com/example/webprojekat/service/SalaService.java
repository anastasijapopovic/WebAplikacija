package com.example.webprojekat.service;

import java.util.List;

import com.example.webprojekat.entity.Sala;

public interface SalaService {

	Sala save(Sala sala);

	Sala findOne(Long id);

	Sala findOznaka(String oznaka);

	List<Sala> findAll();

	void delete(Long id);

}
