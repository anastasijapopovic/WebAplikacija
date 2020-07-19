package com.example.webprojekat.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Gledaoci extends Korisnik {

	@ManyToMany
	@JoinTable(name="odgledani_filmovi",
	joinColumns = @JoinColumn(name = "id_gledaoca", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "odgledan_id", referencedColumnName = "id"))
	private Set<Film> odgledani=new HashSet<>();
	
	@ManyToMany
	@JoinTable(name="rezervisani_filmovi",
	joinColumns = @JoinColumn(name = "id_gledaoca", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "rezervisani_id", referencedColumnName = "id"))
	private Set<Termini> rezervisani=new HashSet<>();
	
	@ManyToMany
	@JoinTable(name="ocenjeni_filmovi",
	joinColumns = @JoinColumn(name = "id_gledaoca", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ocenjeni_id", referencedColumnName = "id"))
	private Set<Ocene> ocenjeni=new HashSet<>();

	public Set<Film> getOdgledani() {
		return odgledani;
	}

	public void setOdgledani(Set<Film> odgledani) {
		this.odgledani = odgledani;
	}

	public Set<Termini> getRezervisani() {
		return rezervisani;
	}

	public void setRezervisani(Set<Termini> rezervisani) {
		this.rezervisani = rezervisani;
	}

	public Set<Ocene> getOcenjeni() {
		return ocenjeni;
	}

	public void setOcenjeni(Set<Ocene> ocenjeni) {
		this.ocenjeni = ocenjeni;
	}
	
	
	/*@Override
	public String toString() {
		return "Gledaoci {id=" + id + ", korisnicko_ime=" + korisnicko + ", lozinka=" + lozinka + ", ime=" + ime
				+ ", prezime=" + prezime + ", telefon=" + telefon + ", mail=" + mail + ", datum_rodjenja="
				+ datum + ", uloga=" + uloga + "}";
	}*/
}