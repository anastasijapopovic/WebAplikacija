package com.example.webprojekat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrator extends Korisnik {

	/*@Override
	public String toString() {
		return "Administrator {id=" + id + ", korisnicko_ime=" + korisnicko + ", lozinka=" + lozinka + ", ime="
				+ ime + ", prezime=" + prezime + ", telefon=" + telefon + ", mail=" + mail + ", datum_rodjenja="
				+ datum + ", uloga=" + uloga + "}";
	}*/
}
