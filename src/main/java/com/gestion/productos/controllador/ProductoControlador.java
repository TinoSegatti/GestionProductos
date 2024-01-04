package com.gestion.productos.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.gestion.productos.entidades.Producto;
import com.gestion.productos.servico.ProductoServicio;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class ProductoControlador {
	
	@Autowired
	private ProductoServicio productoServico;
	

	@RequestMapping("/")
	public String verPaginaDeInicio(Model modelo, @Param("palabraClave")String palabraClave) {
		
		List<Producto> listaProductos = productoServico.listAll(palabraClave);
		
		
		modelo.addAttribute("listaProductos" , listaProductos);
		modelo.addAttribute("palabraClave", palabraClave);
		return "index";
	}
	
	@RequestMapping("/nuevo")
	public String mostrarFormularioDeRegistrarProducto(Model modelo) {
		Producto producto = new Producto();
		modelo.addAttribute("producto", producto);
		return "nuevo_producto";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardarProducto(@ModelAttribute("producto")Producto producto) {
		productoServico.save(producto);
		return "redirect:/";
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView mostrarFormularioDeEditarProducto(@PathVariable(name= "id")Long id) {
		ModelAndView modelo = new ModelAndView("editar_producto");
		
		Producto producto = productoServico.get(id);
		modelo.addObject("producto", producto);
		
		return modelo;
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable(name= "id")Long id) {
		productoServico.delete(id);
		return "redirect:/";
	}
}
