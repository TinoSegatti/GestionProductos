package com.gestion.productos.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.productos.entidades.Producto;
import com.gestion.productos.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	//Listar todos los productos
	public List <Producto> listAll(String palabraClave){
		if(palabraClave != null) {
			return productoRepositorio.findAll(palabraClave);
		}
		return productoRepositorio.findAll();
	}
	
	//guardar producto
	
	public void save(Producto producto) {
		productoRepositorio.save(producto);
	}
	
	//buscar por ID
	
	public Producto get(Long id) {
		return productoRepositorio.findById(id).get();
	}
	
	//eliminar producto
	
	public void delete (Long id) {
		productoRepositorio.deleteById(id);
	}
}
