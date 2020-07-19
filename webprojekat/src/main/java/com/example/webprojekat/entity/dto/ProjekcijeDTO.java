package com.example.webprojekat.entity.dto;

import com.example.webprojekat.entity.Bioskop;
import com.example.webprojekat.entity.Termini;

public class ProjekcijeDTO {
	private Long id;
	private Bioskop bioskop;
	private Termini t;
	
	public ProjekcijeDTO() {}
	public ProjekcijeDTO(Long id, Bioskop bioskop, Termini t) {
		super();
		this.id = id;
		this.bioskop = bioskop;
		this.t = t;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Bioskop getBioskop() {
		return bioskop;
	}
	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	public Termini getT() {
		return t;
	}
	public void setT(Termini t) {
		this.t = t;
	}	
	
}
