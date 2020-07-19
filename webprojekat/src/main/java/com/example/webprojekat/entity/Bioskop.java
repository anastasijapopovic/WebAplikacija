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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bioskop implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String adresa;
	
	@Column
	private Long telefon;
	
	@Column
	private String mail;
	
	public Bioskop() {}

	public Bioskop(String naziv, String adresa, Long telefon, String mail) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.mail = mail;
	}


	//menadzer zaduzen za upravljanje bioskop
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Menadzeri menadzer;
	
	//lista sala koje se nalaze u tom bioskopu
	@OneToMany(mappedBy = "bioskop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sala> sala = new HashSet<>();
	
	//raspored odrzavanja
	@OneToMany(mappedBy = "bioskop")
    private List<Projekcije> projekcije = new ArrayList<>();
	
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


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public Long getTelefon() {
		return telefon;
	}


	public void setTelefon(Long telefon) {
		this.telefon = telefon;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public Menadzeri getMenadzer() {
		return menadzer;
	}


	public void setMenadzer(Menadzeri menadzer) {
		this.menadzer = menadzer;
	}


	public Set<Sala> getSala() {
		return sala;
	}


	public void setSala(Set<Sala> sala) {
		this.sala = sala;
	}

	public List<Projekcije> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(List<Projekcije> projekcije) {
		this.projekcije = projekcije;
	}
	
	/*
	@Override
	public String toString() {
		return "Bioskop {id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", telefon=" + telefon + ", mail="
				+ mail + "}";
	}
	*/
	
}
