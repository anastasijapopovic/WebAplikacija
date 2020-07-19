package com.example.webprojekat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Projekcije;
import com.example.webprojekat.repository.ProjekcijeRepository;
import com.example.webprojekat.service.ProjekcijeService;

@Service	//novo*
public class ProjekcijeServiceImpl implements ProjekcijeService {
	@Autowired
	private ProjekcijeRepository projekcijeRepository;
	
	@Override
	public Projekcije save(Projekcije projekcije)
	{
		return this.projekcijeRepository.save(projekcije);
	}
}
