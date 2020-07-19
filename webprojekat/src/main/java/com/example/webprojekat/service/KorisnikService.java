package com.example.webprojekat.service;

import java.util.List;

import com.example.webprojekat.entity.Korisnik;

public interface KorisnikService {
	//novo*
	Korisnik findOne(Long id);

	List<Korisnik> findAll();

	Korisnik save(Korisnik k);

}
