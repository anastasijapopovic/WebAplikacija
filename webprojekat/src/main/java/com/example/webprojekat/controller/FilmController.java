package com.example.webprojekat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webprojekat.entity.Film;
import com.example.webprojekat.entity.Gledaoci;
import com.example.webprojekat.entity.Termini;
import com.example.webprojekat.entity.dto.FilmDTO;
import com.example.webprojekat.entity.dto.PretragaFilmaDTO;
import com.example.webprojekat.entity.dto.RezervisiDTO;
import com.example.webprojekat.entity.dto.TerminiDTO;
import com.example.webprojekat.service.FilmService;
import com.example.webprojekat.service.GledaociService;
import com.example.webprojekat.service.TerminiService;

@RestController	//novo*
@RequestMapping(value = "/bioskop/filmovi")	//bazni url koji klasa obradjuje
public class FilmController {
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private TerminiService terminiService;
	
	@Autowired
	private GledaociService gledaociService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> getFilmovi(){
		List<Film> filmovi=this.filmService.findAll();
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/sortiranje-naziv",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> sortiranjeNaziv(){
		List<Film> filmovi=this.filmService.poNazivu();		
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}

	@GetMapping(value="/sortiranje-ocena",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> sortiranjeOcena(){
		List<Film> filmovi=this.filmService.poOceni();
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/sortiranje-trajanje",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> sortiranjeTrajanja(){
		List<Film> filmovi=this.filmService.poTrajanju();		
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}

	@PostMapping(value="/pretraga",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> pretraga(@RequestBody PretragaFilmaDTO f)throws Exception{
			List<Termini> projekcije=this.terminiService.findAll();
			List<FilmDTO> filmoviDTO =new ArrayList<>();
			int parametar=0;
			boolean r=true;
			for(Termini termini : projekcije) {
				r=true;
				if(f.getNaziv()!="") {
					if(termini.getFilm().getNaziv().equalsIgnoreCase(f.getNaziv())) {
						parametar=1;
					}else {
						r=false;
					}
				}if(f.getZanr()!="") {
					if(termini.getFilm().getZanr().equalsIgnoreCase(f.getZanr())) {
						parametar=1;
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}else {
						r=false;
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}if(f.getOpis()!="") {
					if(termini.getFilm().getOpis().equalsIgnoreCase(f.getOpis())) {
						parametar=1;
						//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}else {
						r=false;
						//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}if(f.getOcena()!=null) {
					System.out.println(f.getNaziv());
						if(Double.compare(f.getOcena(), termini.getFilm().getOcena())==0) {
							parametar=1;
							//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
						}else {
							r=false;
							//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
						}
				}	
				if(f.getCena()!=0) {
					if(termini.getCena()==f.getCena()) {
						parametar=1;
						//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}else {
						r=false;
						//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}				
				if(parametar==1) {
					if(r==true) {
						FilmDTO filmDTO=new FilmDTO(termini.getFilm().getId(),termini.getFilm().getNaziv(),termini.getFilm().getOpis(),termini.getFilm().getZanr(),termini.getFilm().getTrajanje(),termini.getFilm().getOcena());
						boolean postoji=false;
						for (FilmDTO film : filmoviDTO) {
							if(film.getNaziv().equalsIgnoreCase(filmDTO.getNaziv())) {
								postoji=true;
							}
						}
						if(postoji==false) {
							filmoviDTO.add(filmDTO);
						}
					}
				}
			}			
			if(filmoviDTO.isEmpty()) {
				//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);		
	}
	
	@GetMapping(value="/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TerminiDTO>> prikaz(@PathVariable(name="id") Long id){
		Film f=this.filmService.findOne(id);
		List<TerminiDTO> p=new ArrayList<>();
		List<Termini> termini=f.getTermini();
		if(termini.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		for (Termini t : termini) {
			TerminiDTO term=new TerminiDTO();
			term.setId(t.getId());
			term.setRezervisano(t.getRezervisano());
			term.setNaziv(t.getFilm().getNaziv());
			term.setOpis(t.getFilm().getOpis());
			term.setZanr(t.getFilm().getZanr());
			term.setTrajanje(t.getFilm().getTrajanje());
			term.setOcena(t.getFilm().getOcena());
			term.setCena(t.getCena());
			term.setDatum(t.getDatum());
			term.setOznaka(t.getSale().getOznaka());
			term.setVreme(t.getVreme());
			term.setBioskop(t.getSale().getBioskop().getNaziv());
			p.add(term);
		}
		//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@GetMapping(value="/rezervisi/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TerminiDTO> rezervisi(@PathVariable(name="id")Long id){
			Termini t=this.terminiService.findOne(id);
			TerminiDTO tdto=new TerminiDTO();
			tdto.setId(t.getId());
			
			return new ResponseEntity<>(tdto,HttpStatus.OK);
	}
	
	@PostMapping(value="/rezervacija",
				consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
		        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervisiDTO> rezervacije(@RequestBody RezervisiDTO r)throws Exception{
			Gledaoci g=this.gledaociService.find(r.getKorisnicko(), r.getLozinka());
			RezervisiDTO rdto=new RezervisiDTO();
			rdto.setId(g.getId().toString());
			if(g==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				Long id=Long.parseLong(r.getId());
				Termini termini=this.terminiService.findOne(id);
				if(termini==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Set<Termini> rezervisani=g.getRezervisani();
				if(termini.getSale().getKapacitet()>termini.getRezervisano()) {
					termini.setRezervisano(termini.getRezervisano()+1);
					rezervisani.add(termini);
					this.gledaociService.save(g);
				}else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(rdto,HttpStatus.OK);
			}
	}
	
	//menjati sve dole, novo*
	/*@GetMapping("/filmovi")
	public String filmovi()
	{
		return "Filmovi.html";
	}*/
	
}
