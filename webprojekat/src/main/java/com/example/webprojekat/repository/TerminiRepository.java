package com.example.webprojekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webprojekat.entity.Film;
import com.example.webprojekat.entity.Termini;

public interface TerminiRepository extends JpaRepository<Termini, Long> {
	List<Termini> findAllByFilm(Film film);
}
