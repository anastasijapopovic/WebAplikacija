package com.example.webprojekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webprojekat.entity.Bioskop;
import com.example.webprojekat.entity.Film;
import com.example.webprojekat.entity.Menadzeri;
import com.example.webprojekat.entity.Projekcije;
import com.example.webprojekat.entity.Sala;
import com.example.webprojekat.entity.Termini;
import com.example.webprojekat.entity.dto.BioskopDTO;
import com.example.webprojekat.entity.dto.DodajBioskopDTO;
import com.example.webprojekat.entity.dto.SalaDTO;
import com.example.webprojekat.entity.dto.TerminiDTO;
import com.example.webprojekat.service.BioskopService;
import com.example.webprojekat.service.FilmService;
import com.example.webprojekat.service.GledaociService;
import com.example.webprojekat.service.MenadzeriService;
import com.example.webprojekat.service.ProjekcijeService;
import com.example.webprojekat.service.SalaService;
import com.example.webprojekat.service.TerminiService;

@RestController	//novo*
@RequestMapping(value = "/bioskop")	//bazni url koji klasa obradjuje
public class BioskopController {
	@Autowired
	private BioskopService bioskopService;
	@Autowired
	private MenadzeriService menadzeriService;
	@Autowired
	private GledaociService gledaociService;
	@Autowired
	private SalaService salaService;
	@Autowired 
	private FilmService filmService;
	@Autowired 
	private ProjekcijeService projekcijeService;
	@Autowired
	private TerminiService terminiService;
	
	/*@GetMapping("/bioskopi")
	public String bioskopi(Model model)
	{
		return "Bioskopi.html";
	}*/
	
	@PostMapping(value = "/dodaj-bioskop",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DodajBioskopDTO> dodavanje(@RequestBody DodajBioskopDTO dodaj) throws Exception
	{
		Bioskop bioskop=new Bioskop(dodaj.getNaziv(), dodaj.getAdresa(), dodaj.getTelefon(), dodaj.getMail());
		String m=dodaj.getMenadzer();
		Menadzeri menadzer=this.menadzeriService.findKorisnicko(m);
		if(menadzer==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			bioskop.setMenadzer(menadzer);
			this.bioskopService.savebioskop(bioskop);
			DodajBioskopDTO dto=new DodajBioskopDTO(bioskop.getId(), bioskop.getNaziv(), bioskop.getAdresa(), bioskop.getTelefon(), bioskop.getMail());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/bioskopi",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BioskopDTO>> dobaviBioskop()
	{
		List<Bioskop> bioskopi=this.bioskopService.findAllB();
		List<BioskopDTO> bioskopidto=new ArrayList<>();
		for(Bioskop b : bioskopi)
		{
			BioskopDTO dto=new BioskopDTO(b.getId(), b.getNaziv(), b.getAdresa(), b.getTelefon(), b.getMail());
			bioskopidto.add(dto);
		}
		return new ResponseEntity<>(bioskopidto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bioskopi/ukloni/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> uklanjanje(@PathVariable(name = "id") Long id)
	{
		Bioskop b=this.bioskopService.findOneB(id);
		this.bioskopService.deleteB(id);
		BioskopDTO bioskop=new BioskopDTO(b.getId(), b.getNaziv(), b.getAdresa(), b.getTelefon(), b.getMail());
		return new ResponseEntity<>(bioskop, HttpStatus.OK);
	}
	
	@PostMapping(value = "/dodaj-salu",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> dodavanjeSale(@RequestBody SalaDTO s) throws Exception
	{
		Bioskop b=this.bioskopService.findNazivB(s.getBioskop());
		if(b==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			Sala sala=new Sala(s.getKapacitet(), s.getOznaka(), b);
			Sala nova=this.salaService.save(sala);
			SalaDTO dto=new SalaDTO(nova.getId(), nova.getKapacitet(), nova.getOznaka(), nova.getBioskop().getNaziv());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/dodaj-projekcije",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Projekcije> dodavanjeProjekcija(@RequestBody TerminiDTO t) throws Exception
	{
		Bioskop b=this.bioskopService.findNazivB(t.getBioskop());
		if(b==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Film f=this.filmService.findNaziv(t.getNaziv());
		if(f==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Termini termini=new Termini();
		termini.setFilm(f);
		termini.setRezervisano(0);
		termini.setCena(t.getCena());
		termini.setDatum(t.getDatum());
		termini.setVreme(t.getVreme());
		Sala s=this.salaService.findOznaka(t.getOznaka());
		if(s==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Bioskop b1=new Bioskop(b.getNaziv(), b.getAdresa(), b.getTelefon(), b.getMail());
		Sala s1=new Sala(s.getKapacitet(), s.getOznaka(), b);
		Termini  t1=this.terminiService.save(termini);
		Projekcije p=new Projekcije();
		p.setBioskop(b);
		Projekcije p1=this.projekcijeService.save(p);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@GetMapping(value = "/izmena-sale/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> sala(@PathVariable(name="id") Long id){
		Sala s=this.salaService.findOne(id);
		SalaDTO s1=new SalaDTO();
		s1.setId(s.getId());
		s1.setKapacitet(s.getKapacitet());
		s1.setOznaka(s.getOznaka());
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}
	
	@PostMapping(value="/sala/izmena",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> izmenaSale(@RequestBody SalaDTO s)throws Exception{
		Sala sala=this.salaService.findOne(s.getId());
		if(sala==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		sala.setKapacitet(s.getKapacitet());
		sala.setOznaka(s.getOznaka());
		this.salaService.save(sala);
		SalaDTO s1=new SalaDTO();
		s1.setId(sala.getId());
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}
	
	@GetMapping(value="/izmeni-repertoar/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TerminiDTO> repertoar(@PathVariable(name="id") Long id){
			Termini t=this.terminiService.findOne(id);
			TerminiDTO t1=new TerminiDTO();
			t1.setId(t.getId());
			t1.setNaziv(t.getFilm().getNaziv());
			t1.setDatum(t.getDatum());
			t1.setVreme(t.getVreme());
			t1.setCena(t.getCena());
			t1.setOznaka(t.getSale().getOznaka());
			return new ResponseEntity<>(t1,HttpStatus.OK);
	}
	
	@PostMapping(value="/repertoar/izmenjivanje",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TerminiDTO> repertoarIzmena(@RequestBody TerminiDTO t)throws Exception{
			Termini termini=this.terminiService.findOne(t.getId());
			if(termini==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Film f=this.filmService.findNaziv(t.getNaziv());
			if(f==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Sala s=this.salaService.findOznaka(t.getOznaka());
			if(s==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			if(t.getDatum()!=null) {
				termini.setDatum(t.getDatum());
			}
			termini.setFilm(f);
			termini.setVreme(t.getVreme());
			termini.setCena(t.getCena());
			termini.setSale(s);
			this.terminiService.save(termini);
			TerminiDTO termini1=new TerminiDTO();
			termini1.setId(termini.getId());
			return new ResponseEntity<>(termini1,HttpStatus.OK);
	}
	
	@GetMapping(value="/izmeni-bioskop/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> bioskop(@PathVariable(name="id") Long id){
			Bioskop b=this.bioskopService.findOneB(id);
			BioskopDTO b1=new BioskopDTO();
			b1.setId(b.getId());
			b1.setNaziv(b.getNaziv());
			b1.setAdresa(b.getAdresa());
			b1.setTelefon(b.getTelefon());
			b1.setMail(b.getMail());
			return new ResponseEntity<>(b1,HttpStatus.OK);	
	}
	
	@PostMapping(value="/bioskopi/izmenjeni",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> izmena(@RequestBody BioskopDTO b)throws Exception{
		Bioskop bioskop=this.bioskopService.findOneB(b.getId());
		if(bioskop==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		bioskop.setAdresa(b.getAdresa());
		bioskop.setTelefon(b.getTelefon());
		bioskop.setNaziv(b.getNaziv());
		bioskop.setMail(b.getMail());
		this.bioskopService.savebioskop(bioskop);
		BioskopDTO b1=new BioskopDTO();
		b1.setId(bioskop.getId());
		return new ResponseEntity<>(b1,HttpStatus.OK);	
	}
	
	/*@PostMapping("/dodato")
	public String sacuvajbioskop(@ModelAttribute Bioskop bioskop)
	{
		Bioskop b=new Bioskop();
		b.setNaziv(bioskop.getNaziv());
		b.setAdresa(bioskop.getAdresa());
		b.setTelefon(bioskop.getTelefon());
		b.setMail(bioskop.getMail());
		this.bioskopService.savebioskop(b);
		return "Pocetna.html";
	}
	@GetMapping("/bioskopi")
    public String getBioskopi(Model model) {
        List<Bioskop> listab = this.bioskopService.findAllB();
        model.addAttribute("bioskopi", listab);
        return "Bioskopi.html";
    }
    @GetMapping("/bioskopi/{id}")
    public String getBioskop(@PathVariable(name = "id") Long id, Model model) {
    	Bioskop bioskop = this.bioskopService.findOneB(id);
    	model.addAttribute("bioskop", bioskop);
    	return "Bioskop.html";
    } */
}
