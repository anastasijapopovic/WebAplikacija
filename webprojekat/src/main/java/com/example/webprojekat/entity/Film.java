package com.example.webprojekat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Film implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String opis;
	
	@Column
	private String zanr;
	
	@Column 
	private int trajanje;
	
	@Column
	private Double ocena;
	
	public Film() {}
	
	public Film(Long id, String naziv, String opis, String zanr, int trajanje, Double ocena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.trajanje = trajanje;
		this.ocena = ocena;
	}
	
	//@OneToMany(mappedBy = "film", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private Set<Projekcije> Projekcije = new HashSet<>();
	
	@OneToMany(mappedBy="film",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Ocene> ocene=new HashSet<>();
	
	@OneToMany(mappedBy="film")
	private List<Termini> termini=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public Set<Ocene> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocene> ocene) {
		this.ocene = ocene;
	}

	public List<Termini> getTermini() {
		return termini;
	}

	public void setTermini(List<Termini> termini) {
		this.termini = termini;
	}
	
	/*
	@Override
	public String toString() {
		return "Film {id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", zanr=" + zanr + ", trajanje=" + trajanje
				+ ", ocena=" + ocena + "}";
	}
	*/
	
}
