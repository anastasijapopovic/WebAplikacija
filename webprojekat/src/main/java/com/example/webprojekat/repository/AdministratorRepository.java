package com.example.webprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webprojekat.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	Administrator findByKorisnickoAndLozinka(String korisnicko, String lozinka);
}
