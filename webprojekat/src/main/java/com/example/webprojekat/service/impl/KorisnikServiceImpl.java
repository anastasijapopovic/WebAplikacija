package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Korisnik;
import com.example.webprojekat.repository.KorisnikRepository;
import com.example.webprojekat.service.KorisnikService;

@Service	//novo*
public class KorisnikServiceImpl implements KorisnikService {
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public Korisnik findOne(Long id)
	{
		Korisnik korisnik = this.korisnikRepository.getOne(id);
		return korisnik;
	}
	
	@Override
	public List<Korisnik> findAll()
	{
		List<Korisnik> k = this.korisnikRepository.findAll();
		return k;
	}
	
	@Override
	public Korisnik save(Korisnik k)
	{
		return this.korisnikRepository.save(k);
	}
}
