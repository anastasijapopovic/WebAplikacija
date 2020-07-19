package com.example.webprojekat.entity.dto;

public class OceneDTO {
	private String id;
	private String korisnicko;
	private String lozinka;
	private Double ocena;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Double getOcena() {
		return ocena;
	}
	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
}
