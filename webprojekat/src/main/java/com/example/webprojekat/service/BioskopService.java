package com.example.webprojekat.service;

import java.util.List;

import com.example.webprojekat.entity.Bioskop;

public interface BioskopService {
	//novo*
	Bioskop savebioskop(Bioskop b);
	List<Bioskop> findAllB();
	Bioskop findOneB(Long id);
	Bioskop findNazivB(String naziv);
	void deleteB(Long id);
}
