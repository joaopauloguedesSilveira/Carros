package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.repositories.CarroRepository;
import com.exception.DataIntegrityException;
import javassist.tools.rmi.ObjectNotFoundException;

import com.Domain.Carros;
@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
	public List<Carros> findAll(){
		return carroRepository.findAll();
	}
	
	public Carros findByID(Integer id) throws ObjectNotFoundException {
		Optional<Carros> carro = carroRepository.findById(id);
		return carro.orElseThrow(() ->
			new ObjectNotFoundException("Carro não encontrado! ID: "+ id));
		}
	
	
	public Carros kmSuperior(double km) throws ObjectNotFoundException{
		Optional<Carros> carros = carroRepository.findByOdometroGreaterThan(km);
		return carros.orElseThrow(() ->
		new ObjectNotFoundException("Não encontrado um km superior que esse!"));
	}
	
	public Carros diariaInferior(double diaria) throws ObjectNotFoundException {
		Optional<Carros> carros = carroRepository.findByDiariaLessThan(diaria);
		return carros.orElseThrow(() ->
				new ObjectNotFoundException("Não foi possivel achar uma diaria inferior a essa!"));
	}
	
	public Carros anoEntre(int anoIni, int anoFim) throws ObjectNotFoundException {
		Optional<Carros> carros = carroRepository.findByAnoBetween(anoIni, anoFim);
		return carros.orElseThrow(() -> 
				new ObjectNotFoundException("Não foi encontrado carros entre os anos!"));
	}
	
	public Carros insert(Carros carro) {
		carro.setId(null);
		return carroRepository.save(carro);
	}
	
	public Carros update(Carros carro, Integer id) {
		return carroRepository.save(carro);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		this.findByID(id);
		try {
			carroRepository.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Ocorreu um erro de integridade!");
		}
	}
 }
