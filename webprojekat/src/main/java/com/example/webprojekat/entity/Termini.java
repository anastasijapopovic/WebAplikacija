package com.example.webprojekat.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Termini implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	@JoinColumn
	private Film film;
	
	@Column
	private Date datum;
	@Column
	private String vreme;
	@Column
	private int cena;
	@Column
	private int rezervisano;
	
	public Termini() {}

	public Long getId() {
		return id;
	}
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private Sala sale;

	public void setId(Long id) {
		this.id = id;
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

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSale() {
		return sale;
	}

	public void setSale(Sala sale) {
		this.sale = sale;
	}
	
}
