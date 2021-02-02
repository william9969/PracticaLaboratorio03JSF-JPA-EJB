package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.ejb.ProvinciaFacade;
import ec.edu.ups.ejb.BodegaProductosFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.BodegaProductos;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Productos;
import ec.edu.ups.entidades.Provincia;

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
	@EJB
	private ProvinciaFacade ejbProvinciaFacade;
	
	private String nombreProducto;
	private double precioProducto;
	private int stock;
	private int idBodega;
	private String nombreNoProducto;
	private List<BodegaProductos> listaBodegaProductos;
	private List<Productos> listNoProductos;
	private List<Productos> listProductosTotal;
	private List<Productos> listProductos;
	private Productos productos;
	
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public int getIdBodega() {
		return idBodega;
	}
	public void setIdBodega(int idBodega) {
		this.idBodega = idBodega;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
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
	
	public List<BodegaProductos> getListaBodegaProductos() {
		return listaBodegaProductos;
	}
	public void setListaBodegaProductos(List<BodegaProductos> listaBodegaProductos) {
		this.listaBodegaProductos = listaBodegaProductos;
	}
	
	public Productos getProductos() {
		return productos;
	}
	public void setProductos(Productos productos) {
		this.productos = productos;
	}
	public String method(String nombre) {
		
		return nombre;
	}
	
	
	public String listadoBodega(int idBodega) {
		this.idBodega=idBodega;
		listProductos = ejbProductosFacade.buscarProductosBodega(idBodega);
		for(int i=0;i<listProductos.size();i++) {
			int stock= ejbBodegaProductos.devolverStock(listProductos.get(i).getIdProdcuto(), idBodega);
			listProductos.get(i).setStock(stock);
		}
		//listProductos=ejbProductosFacade.buscarProductosBodega(idBodega);
		List<Productos>produTotal=ejbProductosFacade.findAll();
		listNoProductos=ejbProductosFacade.buscarNoProductosBodega(idBodega,produTotal);
		//System.out.println("La lista de bodegas Productos" +listaBodegaProductos);
		return "/Practica03EJB-JPA-JSF/private/admin/gestionarBodega.xhtml";

	}
	
	
	public String eliminarProductoBodega(Productos producto) {
		int idbp=ejbBodegaProductos.buscarIDPorBodegayProducto(producto.getIdProdcuto(), this.idBodega);
		BodegaProductos bp=ejbBodegaProductos.find(idbp);
		ejbBodegaProductos.remove(bp);
		return null;
	}
	public String agregarProductoBodega() {
		Productos producto = ejbProductosFacade.buscarProductoPorNombre(this.nombreNoProducto);
		Bodega bodega= ejbBodegaFacade.find(this.idBodega);
		
		ejbBodegaProductos.create(new BodegaProductos(bodega, producto, stock));
		listProductos = ejbProductosFacade.buscarProductosBodega(idBodega);
		for(int i=0;i<listProductos.size();i++) {
			int stock= ejbBodegaProductos.devolverStock(listProductos.get(i).getIdProdcuto(), idBodega);
			listProductos.get(i).setStock(stock);
		}
		
		List<Productos>produTotal=ejbProductosFacade.findAll();
		listNoProductos=ejbProductosFacade.buscarNoProductosBodega(idBodega,produTotal);
		this.stock=0;
		return null;
	}
	public String editProductoBodega(Productos productos) {
		productos.setEditable(true);
		return null;
	}
	
	public String saveProductoBodega(Productos productos) {
		int idbp=ejbBodegaProductos.buscarIDPorBodegayProducto(productos.getIdProdcuto(), this.idBodega);
		BodegaProductos bp=ejbBodegaProductos.find(idbp);
		bp.setStock(productos.getStock());
		ejbBodegaProductos.edit(bp);
		productos.setEditable(false);
		return null;
	}
}