package com.example.webprojekat.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Projekcije implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*@Column
	private String dan;
	
	@Column
	private String vreme;
	
	@Column
	private Double cena;*/
	
	//@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	//private Film film;
	
	//@ManyToMany(mappedBy = "projekcije")
	//private Set<Sala> sala = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Termini termini;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn
	private Bioskop bioskop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Termini getTermini() {
		return termini;
	}

	public void setTermini(Termini termini) {
		this.termini = termini;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	/*
	@Override
	public String toString() {
		return "Projekcije {id=" + id + ", dan=" + dan + ", vreme=" + vreme + ", cena=" + cena + ", film=" + film
				+ ", sala=" + sala + ", bioskop=" + bioskop + "}";
	}
	*/	
}
