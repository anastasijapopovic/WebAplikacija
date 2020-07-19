package com.example.webprojekat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Korisnik implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String korisnicko;
	
	@Column
	private String lozinka;
	
	@Column
	private String ime;
	
	@Column
	private String prezime;
	
	@Column
	private String telefon;
	
	@Column
	private String mail;
	
	@Column
	private String datum;
	
	@Column
	private String uloga;
	
	@Column
	private Boolean aktivan;
	
	public Korisnik() {}
	
	public Korisnik(Long id, String korisnicko, String lozinka, String ime, String prezime, String telefon, String mail,
			String datum, String uloga) {
		super();
		this.id = id;
		this.korisnicko = korisnicko;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.mail = mail;
		this.datum = datum;
		this.uloga = uloga;
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

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
}
