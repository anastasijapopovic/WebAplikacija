package com.example.webprojekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webprojekat.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{
	Film findByNazivIgnoreCase(String naziv);
	List<Film> findAllByNazivIgnoreCase(String naziv);
	List<Film> findByNazivIgnoreCaseAndZanrIgnoreCaseAndOpisIgnoreCaseAndOcena(String naziv,String zanr,String opis,Double ocena);
	List<Film> findByOcenaBetween(Double ocena1,Double ocena2);
	List<Film> findAllByOrderByNaziv();
	List<Film> findAllByOrderByOcena();
	List<Film> findAllByOrderByTrajanje();
}
