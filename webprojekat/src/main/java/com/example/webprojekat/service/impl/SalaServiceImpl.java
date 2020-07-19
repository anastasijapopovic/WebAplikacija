package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Sala;
import com.example.webprojekat.repository.SalaRepository;
import com.example.webprojekat.service.SalaService;

@Service
public class SalaServiceImpl implements SalaService {
	@Autowired
	private SalaRepository salaRepository;
	
	@Override
	public Sala save(Sala sala)
	{
		return this.salaRepository.save(sala);
	}
	
	@Override
	public Sala findOne(Long id)
	{
		return this.salaRepository.getOne(id);
	}
	
	@Override
	public Sala findOznaka(String oznaka)
	{
		return this.salaRepository.findByOznakaIgnoreCase(oznaka);
	}
	
	@Override
	public List<Sala> findAll()
	{
		return this.salaRepository.findAll();
	}
	
	@Override
	public void delete(Long id)
	{
		this.salaRepository.deleteById(id);
	}
}
