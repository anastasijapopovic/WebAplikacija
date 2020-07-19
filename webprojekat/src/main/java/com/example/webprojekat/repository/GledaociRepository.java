package com.example.webprojekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webprojekat.entity.Gledaoci;

public interface GledaociRepository extends JpaRepository<Gledaoci, Long>{
	Gledaoci findByKorisnicko(String korisnicko);
	Gledaoci findByKorisnickoAndLozinka(String korisnicko, String lozinka);
	List<Gledaoci> findAllByImeOrPrezimeIgnoreCase(String ime, String prezime);
}
