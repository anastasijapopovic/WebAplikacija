package com.example.webprojekat.entity.dto;

public class MenadzerDTO {
	private Long id;
	private String korisnicko;
	private String ime;
	private String prezime;
	
	public MenadzerDTO(Long id, String korisnicko, String ime, String prezime) {
		super();
		this.id = id;
		this.korisnicko = korisnicko;
		this.ime = ime;
		this.prezime = prezime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnicko() {
		return korisnicko;
	}
	public void setKorisnicko(String korisnicko) {
		this.korisnicko = korisnicko;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
