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
import javax.persistence.OneToMany;

@Entity
public class Menadzeri extends Korisnik {
	//menadzer zaduzen za upravljanje bioskop
	@OneToMany(mappedBy = "menadzer", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Bioskop> bioskop = new HashSet<>();

	public Set<Bioskop> getBioskop() {
		return bioskop;
	}

	public void setBioskop(Set<Bioskop> bioskop) {
		this.bioskop = bioskop;
	}
		
	
	/*@Override
	public String toString() {
		return "Menadzeri {id=" + id + ", korisnicko_ime=" + korisnicko + ", lozinka=" + lozinka + ", ime=" + ime
				+ ", prezime=" + prezime + ", telefon=" + telefon + ", mail=" + mail + ", datum_rodjenja="
				+ datum + ", uloga=" + uloga + "}";
	}*/
	
}