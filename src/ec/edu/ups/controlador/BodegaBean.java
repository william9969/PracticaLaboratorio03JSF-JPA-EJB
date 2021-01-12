package ec.edu.ups.controlador;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.ProvinciaFacade;
import ec.edu.ups.entidades.Productos;
import ec.edu.ups.entidades.Provincia;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BodegaBean implements Serializable {
	
	@EJB
	BodegaFacade ejbBodegaFacade;
	@EJB
	ProvinciaFacade ejbProvinciaFacade;
	
	private String nombre;
	private String telefono;
	private String cuidad;
	private String direccion;
	private Provincia provincia;
	private List<Bodega> listBodega;
	private List<Productos> listProductos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCuidad() {
		return cuidad;
	}
	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<Bodega> getListBodega() {
		return listBodega;
	}
	public void setListBodega(List<Bodega> listBodega) {
		this.listBodega = listBodega;
	}
	public List<Productos> getListProductos() {
		return listProductos;
	}
	public void setListProductos(List<Productos> listProductos) {
		this.listProductos = listProductos;
	}
	
	public String addBodega() {
		ejbBodegaFacade.create(new Bodega(this.nombre, this.telefono, this.cuidad, this.direccion, this.provincia));
		listBodega=ejbBodegaFacade.findAll();
		return null;
	}
	
	public String deleteBodega(Bodega b) {
		ejbBodegaFacade.remove(b);
		listBodega=ejbBodegaFacade.findAll();
		return null;
	}
	
	public String editBodega(Bodega b) {
		b.setEditable(true);
		return null;
	}
	public String saveBodega(Bodega b) {
		ejbBodegaFacade.edit(b);
		b.setEditable(false);
		listBodega=ejbBodegaFacade.findAll();
		return null;
	}
	
	
	
}
