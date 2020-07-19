package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Administrator;
import com.example.webprojekat.repository.AdministratorRepository;
import com.example.webprojekat.service.AdministratorService;

@Service	//novo*
public class AdministratorServiceImpl implements AdministratorService{
	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Override
	public Administrator find(String korisnicko, String lozinka)
	{
		Administrator administrator=this.administratorRepository.findByKorisnickoAndLozinka(korisnicko, lozinka);
		return administrator;
	}
	
	@Override
	public Administrator save(Administrator a)
	{
		return this.administratorRepository.save(a);
	}
	
	@Override
	public Administrator findOne(Long id)
	{
		return this.administratorRepository.getOne(id);
	}
	
	@Override
	public List<Administrator> findAll()
	{
		return this.administratorRepository.findAll();
	}
	
	@Override
	public void delete(Long id)
	{
		this.administratorRepository.deleteById(id);
	}
}
