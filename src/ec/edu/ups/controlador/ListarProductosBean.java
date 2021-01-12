package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.Productos;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class ListarProductosBean implements Serializable{
	private static final long serialVersionUID = 1L;	
	@EJB
	private ProductosFacade ejbProductosFacade;
	private String nombreProducto;
	private double precioProducto;
	private int stockProducto;
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
	
	public String listadoProductiosCategoria(Categoria categoria) {
		
		System.out.println("Si llego la categorias");
		return nombreProducto;
	}
	
}
