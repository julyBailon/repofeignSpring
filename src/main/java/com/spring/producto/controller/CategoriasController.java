package com.spring.producto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.producto.entity.Categoria;
import com.spring.producto.service.CategoriasService;

@RestController
@RequestMapping("/categoria")
public class CategoriasController {
	
CategoriasService categoriasService;
	
	public CategoriasController (CategoriasService categoriasService) {
		this.categoriasService=categoriasService;
		
	}
	
	@GetMapping(value = "/all") 
	public List<Categoria> consultaCategorias(){
	 return categoriasService.consultarCategorias();	
	}

	@GetMapping("{id}")
	private ResponseEntity<List<Categoria>> ConsultaCategoriaPorId(@PathVariable("id") Long id){
		
		return ResponseEntity.ok(categoriasService.findallbyCategoria(id));
	}
	
	@PostMapping(value = "/save")
	private ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
		
		try {
			 
			 return  categoriasService.guardar(categoria);
			
			  
		} catch (Exception e) {
		
			System.out.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	
	@DeleteMapping(value="/delete/{id}")
	private ResponseEntity<Boolean> EliminarCategoria(@PathVariable("id") Long id){
		
		categoriasService.eliminarCategoria(id);
		
		return ResponseEntity.ok(categoriasService.findallbyCategoria(id)!= null);
	}
	
	
	
	@PutMapping("/update")
	private ResponseEntity<Categoria> ActualizarCategoria(@RequestBody Categoria categoria){
		try {
			
			Categoria registroCategoria =categoriasService.actualizarCategoria(categoria);
			
			return ResponseEntity.created(new URI("/categoria/" + registroCategoria.getIdCatalogo())).body(registroCategoria);
			 
			
		} catch (Exception e) {
			
			System.out.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	

	
	

}
