package com.example.webprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webprojekat.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	Sala findByOznakaIgnoreCase(String oznaka);
}
