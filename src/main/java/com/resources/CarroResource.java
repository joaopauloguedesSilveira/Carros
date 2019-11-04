package com.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Domain.Carros;
import com.service.CarroService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="Carros")
public class CarroResource {
	
	private CarroResource carroResource;
	
	@Autowired
	private CarroService carroService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Carros>> findAll(){
		List<Carros> carros = carroService.findAll();
		return ResponseEntity.ok().body(carros);
	}
	
	@RequestMapping(value="/odometro", method=RequestMethod.GET)
	public ResponseEntity<Carros> kmSuperior(@RequestParam (value="odometro")double km) throws ObjectNotFoundException{
		Carros carro = carroService.kmSuperior(km);
		return ResponseEntity.ok().body(carro);
	}
	
	@RequestMapping(value="/diaria", method=RequestMethod.GET)
	public ResponseEntity<Carros> diariainferior(@RequestParam (value="diaria")double diaria) throws ObjectNotFoundException{
		Carros carro = carroService.diariaInferior(diaria);
		return ResponseEntity.ok().body(carro);
	}
	
	@RequestMapping(value="ano", method=RequestMethod.GET)
	public ResponseEntity<Carros> anoEntre(@RequestParam (value="ano")int anoIni, int anoFim) throws ObjectNotFoundException{
		Carros carro = carroService.anoEntre(anoIni, anoFim);
		return ResponseEntity.ok().body(carro);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Carros> insert(@RequestBody Carros carro){
		carro = carroService.insert(carro);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(carro.getId())
				.toUri();
		return ResponseEntity.created(uri).body(carro);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="id/{id}")
	public ResponseEntity<Void> update(@RequestBody Carros carro, @PathVariable Integer id){
		carro.setId(id);
		carro = carroService.update(carro, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/id/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException{
		carroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
