package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.ejb.BodegaProductosFacade;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Productos;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class GestionProductosBodega implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ProductosFacade ejbProductosFacade;
	@EJB
	BodegaFacade ejbBodegaFacade;
	@EJB
	private CategoriaFacade ejbCategoriaFacade;
	@EJB
	private BodegaProductosFacade ejbBodegaProductos;
	
	
	private String nombreProducto;
	private double precioProducto;
	private int stockProducto;
	private String nombreNoProducto;
	private List<Productos> listNoProductos;
	private List<Productos> listProductosTotal;
	private List<Productos> listProductos;
	
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	public int getStockProducto() {
		return stockProducto;
	}
	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}
	public List<Productos> getListProductos() {
		return listProductos;
	}
	public void setListProductos(List<Productos> listProductos) {
		this.listProductos = listProductos;
	}
	
	public String getNombreNoProducto() {
		return nombreNoProducto;
	}
	public void setNombreNoProducto(String nombreNoProducto) {
		this.nombreNoProducto = nombreNoProducto;
	}
	public List<Productos> getListNoProductos() {
		return listNoProductos;
	}
	public void setListNoProductos(List<Productos> listNoProductos) {
		this.listNoProductos = listNoProductos;
	}
	public String method(String nombre) {
		
		return nombre;
	}
	
	public String listadoBodegaGeneral(int idBodega) {
		System.out.println("Id de la Bodega"+idBodega);
		listProductos=ejbProductosFacade.buscarProductosBodega(idBodega);
		listNoProductos=ejbProductosFacade.buscarNoProductosBodega(idBodega);
		return "/private/admin/gestionProductoBodega.xhtml";
		
	}
	
	
	public String eliminarProductoBodega(Productos producto) {
		
		
		return null;
	}
	public String agregarProductoBodega() {
		
		
		return null;
	}
}