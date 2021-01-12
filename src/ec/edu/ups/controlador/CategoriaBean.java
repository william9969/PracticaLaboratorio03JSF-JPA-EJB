package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.CategoriaFacade;

import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Productos;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class CategoriaBean implements Serializable {
	
	@EJB
	private CategoriaFacade ejbCategoriaFacade;
	
	private String nombre;
	private List<Categoria> listCategoria;
	private List<Productos> listProductos;
	
	 @PostConstruct
	 public void init() {
		 
	 }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String addBodega() {
		ejbCategoriaFacade.create(new Categoria(this.nombre));
		listCategoria=ejbCategoriaFacade.findAll();
		return null;
	}
	
	public String deleteBodega(Categoria c) {
		ejbCategoriaFacade.remove(c);
		listCategoria=ejbCategoriaFacade.findAll();
		return null;
	}
	
	public String saveBodega(Categoria c) {
		ejbCategoriaFacade.edit(c);
		listCategoria=ejbCategoriaFacade.findAll();
		return null;
	}
	
}