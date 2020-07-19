package com.example.webprojekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webprojekat.entity.Menadzeri;

public interface MenadzeriRepository extends JpaRepository<Menadzeri, Long> {
	Menadzeri findByKorisnickoAndLozinka(String korisnicko, String lozinka);
	Menadzeri findByKorisnicko(String korisnicko);
	List<Menadzeri> findAllByAktivan(Boolean aktivan);
}
