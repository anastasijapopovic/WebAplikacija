package com.example.webprojekat.entity.dto;

public class SalaDTO {
	private Long id;
	private int kapacitet;
	private String oznaka;
	private String bioskop;
	
	public SalaDTO() {}

	public SalaDTO(Long id, int kapacitet, String oznaka, String bioskop) {
		super();
		this.id = id;
		this.kapacitet = kapacitet;
		this.oznaka = oznaka;
		this.bioskop = bioskop;
	}

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

	public String getBioskop() {
		return bioskop;
	}

	public void setBioskop(String bioskop) {
		this.bioskop = bioskop;
	}
	
}
