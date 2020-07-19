package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Korisnik;
import com.example.webprojekat.entity.Menadzeri;
import com.example.webprojekat.repository.MenadzeriRepository;
import com.example.webprojekat.service.MenadzeriService;

@Service	//novo*
public class MenadzeriServiceImpl implements MenadzeriService {
	@Autowired
	private MenadzeriRepository menadzeriRepository;
	
	@Override
	public Menadzeri findOne(Long id) 
	{
		Menadzeri m=this.menadzeriRepository.getOne(id);
		return m;
	}
	
	@Override
	public Menadzeri find(String korisnicko, String lozinka)
	{
		Menadzeri menadzeri=this.menadzeriRepository.findByKorisnickoAndLozinka(korisnicko, lozinka);
		return menadzeri;
	}
	
	@Override
	public Menadzeri save(Menadzeri m)
	{
		return this.menadzeriRepository.save(m);
	}
	
	@Override
	public List<Menadzeri> findAllM() 
	{
        List<Menadzeri> menadzeri = this.menadzeriRepository.findAll();
        return menadzeri;
    }
	
	@Override
	public void delete(Long id)
	{
		this.menadzeriRepository.deleteById(id);
	}
	
	@Override
	public Menadzeri registracija(Korisnik korisnik) {
		Menadzeri m=new Menadzeri();
		m.setKorisnicko(korisnik.getKorisnicko());
		m.setLozinka(korisnik.getLozinka());
		m.setIme(korisnik.getIme());
		m.setPrezime(korisnik.getPrezime());
		m.setTelefon(korisnik.getTelefon());
		m.setDatum(korisnik.getDatum());
		m.setMail(korisnik.getMail());
		m.setUloga("menadzer");
		m.setAktivan(true);
		return this.menadzeriRepository.save(m);	
	}
	
	@Override
	public Menadzeri findKorisnicko(String korisnicko) {
		Menadzeri m=this.menadzeriRepository.findByKorisnicko(korisnicko);
		return  m;
	}
	
	@Override
	public List<Menadzeri> findAktivan(Boolean aktivan){
		List<Menadzeri> m=this.menadzeriRepository.findAllByAktivan(aktivan);
		return m;
	}
}
