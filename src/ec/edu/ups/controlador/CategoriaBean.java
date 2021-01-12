package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
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
@javax.enterprise.context.RequestScoped
public class CategoriaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private CategoriaFacade ejbCategoriaFacade;
	private List<Categoria> listCategoria;
	private List<Productos> listProductos;
	private String nombre;
	
	
	@PostConstruct
	 public void init() {
		Categoria cuiPersonal = new Categoria("Cuidado Personal");
		Categoria lavanderia = new Categoria("Lavanderia");
		Categoria proteccion = new Categoria("Proteccion");
		Categoria dispensadores = new Categoria("Dispensadores");
		Categoria limpieza = new Categoria("Limpieza del Hogar");
		
		ejbCategoriaFacade.create(cuiPersonal);
		ejbCategoriaFacade.create(lavanderia);
		ejbCategoriaFacade.create(proteccion);
		ejbCategoriaFacade.create(dispensadores);
		ejbCategoriaFacade.create(limpieza);
		listCategoria=ejbCategoriaFacade.findAll();
	 }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	 public List<Categoria> getListCategoria() {
		return listCategoria;
	}
	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}
	public List<Productos> getListProductos() {
		return listProductos;
	}
	public void setListProductos(List<Productos> listProductos) {
		this.listProductos = listProductos;
	}
	public String addBodega() {
		ejbCategoriaFacade.create(new Categoria(this.nombre));
		this.listCategoria=ejbCategoriaFacade.findAll();
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