package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.Productos;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;

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
	
	public String method(String nombre) {
		
		return nombre;
	}

	public String listadoProductiosCategoria(String nombreCategoria) {
		System.out.println("Si llego la categorias");
		System.out.println(nombreCategoria);
		return nombreProducto;
	}
	public String listadoProductosBodega(String nombreBodega) {
		System.out.println("Si llego la categorias");
		System.out.println(nombreBodega);
		return nombreProducto;
	}
}
