package com.example.webprojekat.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.webprojekat.entity.Administrator;
import com.example.webprojekat.entity.Film;
import com.example.webprojekat.entity.Gledaoci;
import com.example.webprojekat.entity.Korisnik;
import com.example.webprojekat.entity.Menadzeri;
import com.example.webprojekat.entity.Ocene;
import com.example.webprojekat.entity.Termini;
import com.example.webprojekat.entity.dto.FilmDTO;
import com.example.webprojekat.entity.dto.MenadzerDTO;
import com.example.webprojekat.entity.dto.OceneDTO;
import com.example.webprojekat.entity.dto.TerminiDTO;
import com.example.webprojekat.service.AdministratorService;
import com.example.webprojekat.service.FilmService;
import com.example.webprojekat.service.GledaociService;
import com.example.webprojekat.service.KorisnikService;
import com.example.webprojekat.service.MenadzeriService;
import com.example.webprojekat.service.TerminiService;

@RestController	//novo*
@RequestMapping(value = "/bioskop")	//bazni url koji klasa obradjuje
public class KorisnikController {
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private GledaociService gledaociService;
	@Autowired
	private MenadzeriService menadzeriService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private FilmService filmService;
	@Autowired
	private TerminiService terminiService;
	
	//pravimo novog korisnika kada popuni svoje podatke prilikom kreiranja novog naloga
	@PostMapping(value = "/novi-korisnik",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> kreirajNalog(@RequestBody Korisnik k) throws Exception
	{
		Korisnik korisnik=new Korisnik(k.getId(), k.getKorisnicko(), k.getLozinka(), k.getIme(), k.getPrezime(), k.getTelefon(), k.getMail(), k.getDatum(), k.getUloga());
		Korisnik novi=gledaociService.registracija(korisnik);
		return new ResponseEntity<>(novi, HttpStatus.OK);
	}
	
	//kada se korisnik registruje mora se proveriti u bazi da li postoji nalog sa tim korisnickim imenom i lozinkom
	@PostMapping(value = "/registracija",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> registracija(@RequestBody Korisnik k) throws Exception
	{
		Gledaoci g=this.gledaociService.find(k.getKorisnicko(), k.getLozinka());
		Menadzeri m=this.menadzeriService.find(k.getKorisnicko(), k.getLozinka());
		Administrator a=this.administratorService.find(k.getKorisnicko(), k.getLozinka());
		if(g!=null)	//ako je pronadjen gledaoc
		{
			Korisnik odgovor=new Korisnik(g.getId(), g.getKorisnicko(), g.getLozinka(), g.getIme(), g.getPrezime(), g.getTelefon(), g.getDatum(), g.getMail(), g.getUloga());
			System.out.println(odgovor.getMail());
			return new ResponseEntity<>(odgovor, HttpStatus.OK);
		} else if(m!=null) {
			Korisnik odgovor=new Korisnik(m.getId(), m.getKorisnicko(), m.getLozinka(), m.getIme(), m.getPrezime(), m.getTelefon(), m.getDatum(), m.getMail(), m.getUloga());
			System.out.println(odgovor.getMail());
			return new ResponseEntity<>(odgovor, HttpStatus.OK);
		} else if(a!=null) {
			Korisnik odgovor=new Korisnik(a.getId(), a.getKorisnicko(), a.getLozinka(), a.getIme(), a.getPrezime(), a.getTelefon(), a.getDatum(), a.getMail(), a.getUloga());
			return new ResponseEntity<>(odgovor, HttpStatus.OK);			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	//kada admin unosi novog menadzera
	@PostMapping(value = "/novi-menadzer",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> kreirajMenadzera(@RequestBody Korisnik k) throws Exception
	{
		Korisnik korisnik=new Korisnik(k.getId(), k.getKorisnicko(), k.getLozinka(), k.getIme(), k.getPrezime(), k.getTelefon(), k.getMail(), k.getDatum(), k.getUloga());
		Korisnik novi=menadzeriService.registracija(korisnik);
		return new ResponseEntity<>(novi, HttpStatus.OK);
	}
	//lista menadzera
	@GetMapping(value="/lista-menadzera",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MenadzerDTO>> listaMenadzera()
	{
		List<Menadzeri> menadzeri=this.menadzeriService.findAllM();
		List<MenadzerDTO> menadzerDTO=new ArrayList<>();
		for (Menadzeri m : menadzeri)
		{
			MenadzerDTO mDTO=new MenadzerDTO(m.getId(), m.getKorisnicko(),m.getIme(), m.getPrezime());
			menadzerDTO.add(mDTO);	
		}
		return new ResponseEntity<>(menadzerDTO,HttpStatus.OK);
	}	
	//brisanje menadzera
	@GetMapping(value = "/obrisi-menadzera/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)  
	public ResponseEntity<MenadzerDTO> obrisiMenadzera(@PathVariable(name="id") Long id){
		Menadzeri m=this.menadzeriService.findOne(id);
		MenadzerDTO mDTO=new MenadzerDTO(m.getId(), m.getKorisnicko(), m.getIme(), m.getPrezime());
		this.menadzeriService.delete(id);
		return new ResponseEntity<>(mDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/rezervisane-karte-gledaoc/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TerminiDTO>> rezervisaneKarte(@PathVariable(name="id")Long id){
		 Gledaoci g=this.gledaociService.findOne(id);
		 Set<Termini> karte=g.getRezervisani();
		 List<TerminiDTO> povratna=new ArrayList<>();
		for (Termini t : karte) {
			TerminiDTO termini=new TerminiDTO();
			termini.setId(t.getId());
			termini.setRezervisano(t.getRezervisano());
			termini.setNaziv(t.getFilm().getNaziv());
			termini.setCena(t.getCena());
			termini.setDatum(t.getDatum());
			termini.setOznaka(t.getSale().getOznaka());
			termini.setVreme(t.getVreme());
			termini.setBioskop(t.getSale().getBioskop().getNaziv());
			termini.setIdgledaoca(g.getId());
			povratna.add(termini);
		}
		return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	//filmovi koje je gledaoc odgledao
	@GetMapping(value="/odgledani-filmovi-gledaoc/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> odgledaniFilmovi(@PathVariable(name="id") Long id){
		Gledaoci g=this.gledaociService.findOne(id);
		Set<Ocene> filmovi=g.getOcenjeni();
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		for(Ocene f : filmovi) 
		{
			FilmDTO fDTO=new FilmDTO();
			fDTO.setId(f.getFilm().getId());
			fDTO.setNaziv(f.getFilm().getNaziv());
			fDTO.setZanr(f.getFilm().getZanr());
			fDTO.setOpis(f.getFilm().getOpis());
			fDTO.setTrajanje(f.getFilm().getTrajanje());
			fDTO.setOcena(f.getOcena());
			filmoviDTO.add(fDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/ocenjeni-filmovi-gledaoc/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> ocenjeniFilmovi(@PathVariable(name="id") Long id){
			Gledaoci g=this.gledaociService.findOne(id);
			Set<Ocene> ocene=g.getOcenjeni();
			List<FilmDTO> filmoviDTO=new ArrayList<>();
			
			for (Ocene o : ocene) {
				FilmDTO fdto=new FilmDTO();
				fdto.setId(o.getFilm().getId());
				fdto.setNaziv(o.getFilm().getNaziv());
				fdto.setZanr(o.getFilm().getZanr());
				fdto.setOpis(o.getFilm().getOpis());
				fdto.setTrajanje(o.getFilm().getTrajanje());
				fdto.setOcena(o.getOcena());
				filmoviDTO.add(fdto);
			}		
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/neocenjeni-filmovi-gledaoc/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> neocenjeniFilmovi(@PathVariable(name="id") Long id){
			Gledaoci g=this.gledaociService.findOne(id);
			Set<Film> odgledani=g.getOdgledani();
			Set<Ocene> ocene=g.getOcenjeni();
			Set<Film> ocenjeni=new HashSet<>();
			Film film=new Film();
			for(Ocene o : ocene) {  //lista ocenjenih filmova
				film=o.getFilm();
				ocenjeni.add(film);
			}
			List<FilmDTO> neocenjeni=new ArrayList<>();
			for(Film f : odgledani) {
				if(!ocenjeni.contains(f)) {
					FilmDTO fd=new FilmDTO();
					fd.setId(f.getId());
					fd.setNaziv(f.getNaziv());
					fd.setZanr(f.getZanr());
					fd.setOpis(f.getOpis());
					fd.setTrajanje(f.getTrajanje());
					fd.setOcena(f.getOcena());
					fd.setIdgledaoca(g.getId());
					neocenjeni.add(fd);
				}
			}
		return new ResponseEntity<>(neocenjeni,HttpStatus.OK);	
	}
	
	@GetMapping(value="/gledaoc-otkazuje-rezervaciju/{id}/{value}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gledaoci> otkazivanje(@PathVariable(name="id") Long id,@PathVariable(name="value") Long gledaocID){
		Termini termini=this.terminiService.findOne(id);
		termini.setRezervisano(termini.getRezervisano()-1);
		if(termini==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Gledaoci g=this.gledaociService.findOne(gledaocID);
		if(g==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Set<Termini> rezervisani=g.getRezervisani();
		rezervisani.remove(termini);
		this.gledaociService.save(g);
		Gledaoci g1=new Gledaoci();
		g1.setId(g.getId());
		return new ResponseEntity<>(g1,HttpStatus.OK);		
	}
	
	@GetMapping(value="/gledaoc-potvrdjuje-rezervaciju/{id}/{value}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gledaoci> potvrda(@PathVariable(name="id") Long id,@PathVariable(name="value") Long gledaocID){
		Termini termini=this.terminiService.findOne(id);
		termini.setRezervisano(termini.getRezervisano()-1);
		if(termini==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Gledaoci g=this.gledaociService.findOne(gledaocID);
		if(g==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Set<Termini> rezervisani=g.getRezervisani();
		rezervisani.remove(termini);
		Set<Film> odgledani=g.getOdgledani();
		odgledani.add(termini.getFilm());
		this.gledaociService.save(g);
		Gledaoci g1=new Gledaoci();
		g1.setId(g.getId());			
		return new ResponseEntity<>(g1,HttpStatus.OK);		
	}
	
	@GetMapping(value="/oceni-film-gledaoc/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> oceni(@PathVariable(name="id") Long id){
			Film f=this.filmService.findOne(id);
			Film f1=new Film();
			f1.setId(f.getId());
			return new ResponseEntity<>(f1,HttpStatus.OK);
	}
	
	@PostMapping(value="/ocenjivanje",
				consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
		        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OceneDTO> ocenjivanje(@RequestBody OceneDTO o)throws Exception{
			Gledaoci g=this.gledaociService.find(o.getKorisnicko(), o.getLozinka());
			OceneDTO oDTO=new OceneDTO();
			oDTO.setId(o.getId().toString());
			if(g==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				Long id=Long.parseLong(o.getId());
				Film f=this.filmService.findOne(id);
				if(f==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Double ocena=o.getOcena();
				Double srednjaOcena=(ocena+f.getOcena()/2);
				f.setOcena(srednjaOcena);
				Set<Ocene> ocenjeni=g.getOcenjeni();
				Ocene o1=new Ocene();
				o1.setFilm(f);
				o1.setOcena(ocena);
				ocenjeni.add(o1);
				this.gledaociService.save(g);
			}
			return new ResponseEntity<>(oDTO,HttpStatus.OK);
	}
}
