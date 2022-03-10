package com.spring.producto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.producto.entity.Producto;
import com.spring.producto.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	ProductoService productoService;
	
	public ProductoController(ProductoService productoService) {
		this.productoService=productoService;
	}
	
	

	@GetMapping(value = "/all") 
	public List<Producto> consultaProductos(){
		return productoService.consultarProductos();
	}
	
	
	@GetMapping("{id}")
	private ResponseEntity<List<Producto>> ConsultaProductoPorId(@PathVariable("id") Long id){
		
		return ResponseEntity.ok(productoService.findallbyProducto(id));
	}
	
	
	@PostMapping(value = "/save")
	private ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
		
		try {
			 
			 return  productoService.guardarProducto(producto);
			
			  
		} catch (Exception e) {
		 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		}
	}
	
	@DeleteMapping(value="/delete/{id}")
	private ResponseEntity<Boolean> EliminarProducto(@PathVariable("id") Long id){
		
		productoService.eliminarCategoria(id);
		
		return ResponseEntity.ok(productoService.findallbyProducto(id)!= null);
	}
	
	

	
	
}
