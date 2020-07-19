package com.example.webprojekat.service;

import java.util.List;

import com.example.webprojekat.entity.Korisnik;
import com.example.webprojekat.entity.Menadzeri;

public interface MenadzeriService {
	//novo*
	Menadzeri find(String korisnicko, String lozinka);
	Menadzeri save(Menadzeri m);
	List<Menadzeri> findAllM();
	Menadzeri findOne(Long id);
	void delete(Long id);
	Menadzeri registracija(Korisnik korisnik);
	Menadzeri findKorisnicko(String korisnicko);
	List<Menadzeri> findAktivan(Boolean aktivan);
}
