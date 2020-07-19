package com.example.webprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webprojekat.entity.Bioskop;

public interface BioskopRepository extends JpaRepository<Bioskop, Long> {
	Bioskop findByNazivIgnoreCase(String naziv);
}
