package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Gledaoci;
import com.example.webprojekat.entity.Korisnik;
import com.example.webprojekat.repository.GledaociRepository;
import com.example.webprojekat.service.GledaociService;

@Service	//novo*
public class GledaociServiceImpl implements GledaociService {
	@Autowired
	private GledaociRepository gledaociRepository;
	
	@Override
	public Gledaoci findOne(Long id)
	{
		Gledaoci g = this.gledaociRepository.getOne(id);
		return g;
	}
	
	@Override
	public List<Gledaoci> findAll()
	{
		List<Gledaoci> gledaoci=this.gledaociRepository.findAll();
		return gledaoci;
	}
	
	@Override
	public Gledaoci find(String korisnicko, String lozinka)
	{
		Gledaoci gledaoci=this.gledaociRepository.findByKorisnickoAndLozinka(korisnicko, lozinka);
		return gledaoci;
	}
	
	@Override
	public Gledaoci save(Gledaoci g)
	{
		return this.gledaociRepository.save(g);
	}
	
	@Override
	public void delete(Long id)
	{
		this.gledaociRepository.deleteById(id);
	}
	
	@Override
	public Gledaoci registracija(Korisnik gledaoc)
	{
		Gledaoci g = new Gledaoci();
		g.setKorisnicko(gledaoc.getKorisnicko());
		g.setLozinka(gledaoc.getLozinka());
		g.setIme(gledaoc.getIme());
		g.setPrezime(gledaoc.getPrezime());
		g.setMail(gledaoc.getMail());
		g.setDatum(gledaoc.getDatum());
		g.setTelefon(gledaoc.getTelefon());
		g.setUloga("gledaoc");
		g.setAktivan(true);
		return this.gledaociRepository.save(g);
	}
	
	@Override
	public Gledaoci logovanje(String korisnicko) throws Exception
	{
		Gledaoci gledaoc = this.gledaociRepository.findByKorisnicko(korisnicko);
		if(gledaoc==null)
			throw new Exception("Ne postoji gledaoc pod tim korisnickim imenom!");
		else
			return gledaoc;
	}
}
