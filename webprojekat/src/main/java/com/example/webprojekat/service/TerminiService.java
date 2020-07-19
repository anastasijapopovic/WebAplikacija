package com.example.webprojekat.service;

import java.util.List;

import com.example.webprojekat.entity.Film;
import com.example.webprojekat.entity.Termini;

public interface TerminiService {

	Termini findOne(Long id);

	List<Termini> findAll();

	List<Termini> findAllFilm(Film f);

	Termini save(Termini t);

}
