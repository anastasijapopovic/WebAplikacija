package com.example.webprojekat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class Sala implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int kapacitet;
	
	@Column(name = "oznaka_sale")
	private String oznaka;
	
	public Sala() {}

	public Sala(int kapacitet, String oznaka, Bioskop bioskop) {
		super();
		this.kapacitet = kapacitet;
		this.oznaka = oznaka;
		this.bioskop = bioskop;
	}

	//lista sala koje se nalaze u tom bioskopu
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Bioskop bioskop;
	
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<Termini> termini=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
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
		return "Sala {id=" + id + ", kapacitet=" + kapacitet + ", oznaka=" + oznaka + "}";
	}
	*/
	
}
