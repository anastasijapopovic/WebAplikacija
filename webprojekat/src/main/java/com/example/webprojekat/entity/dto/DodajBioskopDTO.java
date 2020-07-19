package com.example.webprojekat.entity.dto;

public class DodajBioskopDTO {
	private Long id;
	private String naziv;
	private String adresa;
	private Long telefon;
	private String mail;
	private String menadzer;
	
	public DodajBioskopDTO(Long id, String naziv, String adresa, Long telefon, String mail) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.mail = mail;
	}
	
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
	public String getMenadzer() {
		return menadzer;
	}
	public void setMenadzer(String menadzer) {
		this.menadzer = menadzer;
	}
}
