package com.example.webprojekat.entity.dto;

import java.sql.Date;

public class TerminiDTO {
	private Long id;
	private String naziv;
	private String zanr;
	private String opis;
	private int trajanje;
	private Double ocena;
	private Date datum;
	private String vreme;
	private int cena;
	private int rezervisano;
	private String oznaka;
	private String bioskop;
	private Long idgledaoca;
	
	public TerminiDTO() {}

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

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getRezervisano() {
		return rezervisano;
	}

	public void setRezervisano(int rezervisano) {
		this.rezervisano = rezervisano;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getBioskop() {
		return bioskop;
	}

	public void setBioskop(String bioskop) {
		this.bioskop = bioskop;
	}

	public Long getIdgledaoca() {
		return idgledaoca;
	}

	public void setIdgledaoca(Long idgledaoca) {
		this.idgledaoca = idgledaoca;
	}
	
}
