package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Film;
import com.example.webprojekat.entity.Termini;
import com.example.webprojekat.repository.TerminiRepository;
import com.example.webprojekat.service.TerminiService;

@Service
public class TerminiServiceImpl implements TerminiService {
	@Autowired
	private TerminiRepository terminiRepository;
	
	@Override
	public Termini findOne(Long id)
	{
		Termini projekcije=this.terminiRepository.getOne(id);
		return projekcije;
	}
	
	@Override
	public List<Termini> findAll()
	{
		List<Termini> projekcije=this.terminiRepository.findAll();
		return projekcije;
	}
	
	@Override
	public List<Termini> findAllFilm(Film f)
	{
		List<Termini> projekcije=this.terminiRepository.findAllByFilm(f);
		return projekcije;
	}
	
	@Override
	public Termini save(Termini t)
	{
		Termini projekcije=this.terminiRepository.save(t);
		return projekcije;
	}
}
