package com.example.webprojekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webprojekat.entity.Bioskop;
import com.example.webprojekat.repository.BioskopRepository;
import com.example.webprojekat.service.BioskopService;

@Service	//novo*
public class BioskopServiceImpl implements BioskopService {
	@Autowired
	private BioskopRepository bioskopRepository;
	
	@Override
	public Bioskop savebioskop(Bioskop b)
	{
		return this.bioskopRepository.save(b);
	}
	
	@Override
	public List<Bioskop> findAllB() {
        List<Bioskop> bioskopi = this.bioskopRepository.findAll();
        return bioskopi;
    }
	
	@Override
	public Bioskop findOneB(Long id) {
        Bioskop bioskop = this.bioskopRepository.getOne(id);
        return bioskop;
    }
	
	@Override
	public Bioskop findNazivB(String naziv)
	{
		Bioskop bioskop = this.bioskopRepository.findByNazivIgnoreCase(naziv);
		return bioskop;
	}
	
	@Override
	public void deleteB(Long id)
	{
		this.bioskopRepository.deleteById(id);
	}
}
