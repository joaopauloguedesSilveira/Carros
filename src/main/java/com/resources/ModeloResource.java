package com.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Domain.Modelo;
import com.service.ModeloService;

@RestController
@RequestMapping(value="Modelos")
public class ModeloResource {
	private ModeloResource modeloResource;
	
	@Autowired
	private ModeloService modeloService;
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<Modelo>> findAll(){
		List<Modelo> modelos = modeloService.findAll();
		return ResponseEntity.ok().body(modelos);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Modelo> findById(@PathVariable Integer id){
		Modelo modelo = modeloService.findById(id);
		return ResponseEntity.ok().body(modelo);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Modelo> insert(@RequestBody Modelo modelo){
		modelo = modeloService.insert(modelo);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(modelo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(modelo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/id/{id}")
	public ResponseEntity<Void> update(@RequestBody Modelo modelo, @PathVariable Integer id){
		modelo.setId(id);
		modelo = modeloService.update(modelo, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/id/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		modeloService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
