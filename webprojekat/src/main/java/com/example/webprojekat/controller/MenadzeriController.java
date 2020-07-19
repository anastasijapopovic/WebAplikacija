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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webprojekat.entity.Bioskop;
import com.example.webprojekat.entity.Menadzeri;
import com.example.webprojekat.entity.Projekcije;
import com.example.webprojekat.entity.Sala;
import com.example.webprojekat.entity.dto.BioskopDTO;
import com.example.webprojekat.entity.dto.SalaDTO;
import com.example.webprojekat.entity.dto.TerminiDTO;
import com.example.webprojekat.service.BioskopService;
import com.example.webprojekat.service.MenadzeriService;
import com.example.webprojekat.service.SalaService;

@RestController
@RequestMapping(value = "/bioskop")
public class MenadzeriController {
	@Autowired
	private MenadzeriService menadzeriService;
	
	@Autowired
	private BioskopService bioskopService;
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping(value="/bioskopi/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BioskopDTO>> bioskopi(@PathVariable(name="id")Long id){
			Menadzeri m=this.menadzeriService.findOne(id);
			Set<Bioskop> bioskop=m.getBioskop();
			List<BioskopDTO> povratna=new ArrayList<>();
			
			for(Bioskop b:bioskop) {
				BioskopDTO bdto=new BioskopDTO();
				bdto.setId(b.getId());
				bdto.setNaziv(b.getNaziv());
				bdto.setAdresa(b.getAdresa());
				bdto.setTelefon(b.getTelefon());
				bdto.setMail(b.getMail());
				povratna.add(bdto);
			}
			return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	@GetMapping(value="/sala/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> sala(@PathVariable(name="id") Long id){
			Bioskop b=this.bioskopService.findOneB(id);
			if(b==null) {
				//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Set<Sala> sale=b.getSala();
			if(sale.isEmpty()) {
				//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			List<SalaDTO> povratna=new ArrayList<>();
			for(Sala s:sale) {
				SalaDTO sdto=new SalaDTO();
				sdto.setId(s.getId());
				sdto.setKapacitet(s.getKapacitet());
				sdto.setOznaka(s.getOznaka());
				sdto.setBioskop(b.getNaziv());
				povratna.add(sdto);
			}
		return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	@GetMapping(value="/ukloni-salu/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> ukloniSalu(@PathVariable(name="id")Long id){
			Sala s=this.salaService.findOne(id);
			SalaDTO salaDTO=new SalaDTO(s.getId(), s.getKapacitet(), s.getOznaka(), s.getBioskop().getNaziv());
			this.salaService.delete(id);
			return new ResponseEntity<>(salaDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/repertoar/{id}",   
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TerminiDTO>> repertoar(@PathVariable(name="id")Long id){
			Bioskop b=this.bioskopService.findOneB(id);
			List<TerminiDTO> povratna=new ArrayList<>();
			List<Projekcije> projekcije=b.getProjekcije();
			if(projekcije.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			for(Projekcije p:projekcije) {
				TerminiDTO t=new TerminiDTO();
				t.setId(p.getTermini().getId());
				t.setBioskop(b.getNaziv());
				t.setOznaka(p.getTermini().getSale().getOznaka());
				t.setDatum(p.getTermini().getDatum());
				t.setNaziv(p.getTermini().getFilm().getNaziv());
				t.setVreme(p.getTermini().getVreme());
				t.setOznaka(p.getTermini().getSale().getOznaka());
				t.setCena(p.getTermini().getCena());
				t.setRezervisano(p.getTermini().getRezervisano());
				povratna.add(t);
			}
		return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
}
