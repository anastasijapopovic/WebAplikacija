package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Film;
import com.example.webprojekat.repository.FilmRepository;
import com.example.webprojekat.service.FilmService;

@Service	//novo*
public class FilmServiceImpl implements FilmService {
	@Autowired
	private FilmRepository filmRepository;
	
	@Override
	public Film findOne(Long id)
	{
		Film film = this.filmRepository.getOne(id);
		return film;
	}
	
	@Override
	public List<Film> findAll()
	{
		List<Film> filmovi = this.filmRepository.findAll();
		return filmovi;
	}
	
	@Override
	public List<Film> poNazivu()
	{
		return this.filmRepository.findAllByOrderByNaziv();
	}
	
	@Override
	public List<Film> poOceni()
	{
		return this.filmRepository.findAllByOrderByOcena();
	}
	
	@Override
	public List<Film> poTrajanju()
	{
		return this.filmRepository.findAllByOrderByTrajanje();
	}
	
	@Override
	public List<Film> findAllNaziv(String naziv)
	{
		List<Film> filmovi=this.filmRepository.findAllByNazivIgnoreCase(naziv);
		return filmovi;
	}
	
	@Override
	public List<Film> findAllKriterijumiPretrage(String naziv, String zanr, String opis, Double ocena)
	{
		List<Film> filmovi=this.filmRepository.findByNazivIgnoreCaseAndZanrIgnoreCaseAndOpisIgnoreCaseAndOcena(naziv, zanr, opis, ocena);
		return filmovi;
	}
	
	@Override
	public Film findNaziv(String n)
	{
		Film film=this.filmRepository.findByNazivIgnoreCase(n);
		return film;
	}
}
