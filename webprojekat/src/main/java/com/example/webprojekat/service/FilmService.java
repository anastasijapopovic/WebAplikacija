package com.example.webprojekat.service;

import java.util.List;

import com.example.webprojekat.entity.Film;

public interface FilmService {	//novo*

	Film findOne(Long id);

	List<Film> findAll();

	List<Film> poNazivu();

	List<Film> poOceni();

	List<Film> poTrajanju();

	List<Film> findAllNaziv(String naziv);

	List<Film> findAllKriterijumiPretrage(String naziv, String zanr, String opis, Double ocena);

	Film findNaziv(String n);

	
}
