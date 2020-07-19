package com.example.webprojekat.entity.dto;

public class RezervisiDTO {
	private String id;
	private String korisnicko;
	private String lozinka;
	
	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
	public String getKorisnicko() {
		return korisnicko;
	}
	public void setKorisnicko(String korisnicko) {
		this.korisnicko = korisnicko;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
}
