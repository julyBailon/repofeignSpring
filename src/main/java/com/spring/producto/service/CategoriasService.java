package com.spring.producto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.producto.entity.Categoria;
import com.spring.producto.repository.CategoriasRepository;

@Service
public class CategoriasService {
	private CategoriasRepository categoriasRepository;
	

	@Autowired
	public CategoriasService(CategoriasRepository categoriasRepository) {
		this.categoriasRepository=categoriasRepository;
	}
	
	public List<Categoria> consultarCategorias() {
		
		List<Categoria>c= null ;
		
		
		try {
			
			c=categoriasRepository.findAll();	
		} catch (Exception e) {
			System.out.print(e); 
			
		}
		
		return c;
		//return categoriasRepository.findAll();
	}
	
	public ResponseEntity<Categoria>  guardar(Categoria categoria) {
		Categoria obj=categoriasRepository.save(categoria);
		
		return new ResponseEntity<Categoria>(obj,HttpStatus.OK);
	}

	
	public List<Categoria> findallbyCategoria(long id){
		List<Categoria> categoriaRespuesta=new ArrayList<>();
		List<Categoria> categoria=categoriasRepository.findAll();
		for(int i=0;i<categoria.size();i++) {
			if(categoria.get(i).getIdCatalogo()==id) {
				categoriaRespuesta.add(categoria.get(i));
			}
			
		}
		
		return categoriaRespuesta;
	}
	
	
	
	

	public <S extends Categoria> S actualizarCategoria(S entity) {
		Categoria cat =new Categoria();
		cat.setIdCatalogo(entity.getIdCatalogo());
		cat.setDesc_catalogo(entity.getDesc_catalogo());
		return categoriasRepository.save(entity);
	}

	
	public void eliminarCategoria(Long numCategoria) {
		List<Categoria> categoriaRespuesta=new ArrayList<>();
		categoriaRespuesta=findallbyCategoria(numCategoria);
		Categoria cat = new Categoria();
		
		for (Categoria categoria : categoriaRespuesta) {
			cat=categoria;
		
			 if(cat != null){
					categoriasRepository.delete(cat);
				}
		}
		 	 
	}	

	

	
	
	

}
