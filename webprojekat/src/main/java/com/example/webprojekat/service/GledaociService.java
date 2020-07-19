package com.example.webprojekat.service;

import java.util.List;


import com.example.webprojekat.entity.Gledaoci;
import com.example.webprojekat.entity.Korisnik;

public interface GledaociService {
	//novo*
	Gledaoci findOne(Long id);
	List<Gledaoci> findAll();
	Gledaoci find(String korisnicko, String lozinka);
	Gledaoci save(Gledaoci g);
	void delete(Long id);
	Gledaoci registracija(Korisnik gledaoc);
	Gledaoci logovanje(String korisnicko) throws Exception;
}
