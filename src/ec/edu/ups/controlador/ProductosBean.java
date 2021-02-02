package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Productos;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class ProductosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProductosFacade ejbProductosFacade;
	@EJB
	private CategoriaFacade ejbCategoriaFacade;
	
	private List<Productos> listProductos;
	private String nombreCategoria;
	
	private int idProdcuto;
	
	private String nombreProducto;
	private double precioProducto;
	//private int stockProducto;
	private List<Bodega> listbodegaProd;
	private Categoria catProd;
	
	@PostConstruct
	public void init() {

		listProductos=ejbProductosFacade.findAll();
	}
	
	public String add() {
		catProd =ejbCategoriaFacade.buscarCategoriaPorNombre(nombreCategoria);
		Productos producto = new Productos(nombreProducto,precioProducto,catProd);
		ejbProductosFacade.create(producto);
		this.listProductos = ejbProductosFacade.findAll();
		nombreProducto=null;
		precioProducto=0.0;
		//stockProducto=0;
		catProd=null;
		return null;
	}
	
	public String delete(Productos productos) {
		ejbProductosFacade.remove(productos);
		listProductos = ejbProductosFacade.findAll();
		return null;
	}
	
	public String edit(Productos productos) {
		productos.setEditable(true);
		return null;
	}
	
	public String save(Productos productos) {
		ejbProductosFacade.edit(productos);
		productos.setEditable(false);
		return null;
	}

	public ProductosFacade getEjbProductosFacade() {
		return ejbProductosFacade;
	}

	public void setEjbProductosFacade(ProductosFacade ejbProductosFacade) {
		this.ejbProductosFacade = ejbProductosFacade;
	}

	public List<Productos> getListProductos() {
		return listProductos;
	}

	public void setListProductos(List<Productos> listProductos) {
		this.listProductos = listProductos;
	}

	public int getIdProdcuto() {
		return idProdcuto;
	}

	public void setIdProdcuto(int idProdcuto) {
		this.idProdcuto = idProdcuto;
	}

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

	/*public int getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}*/

	public Categoria getCatProd() {
		return catProd;
	}

	public void setCatProd(Categoria catProd) {
		this.catProd = catProd;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}


	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}


	public List<Bodega> getListbodegaProd() {
		return listbodegaProd;
	}
	public void setListbodegaProd(List<Bodega> listbodegaProd) {
		this.listbodegaProd = listbodegaProd;
	}
	 public void newProducto() {
	        this.nombreProducto = null;
	        this.precioProducto = 0.0;
	        //this.stockProducto = 0;
	    }
}
