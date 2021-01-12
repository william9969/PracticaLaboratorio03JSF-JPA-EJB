package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Productos;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProductosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProductosFacade ejbProductosFacade;
	
	private List<Productos> list;
	
	private int idProdcuto;
	
	private String nombreProducto;
	private double precioProducto;
	private int stockProducto;
	
	private Categoria catProd;
	
	public String add() {
		if (this.catProd != null) {
			Productos productos = new Productos();
			productos.setNombreProducto(this.nombreProducto);
			productos.setPrecioProducto(this.precioProducto);
			productos.setStockProducto(this.stockProducto);
			productos.setCatProd(this.catProd);
			
			ejbProductosFacade.create(productos);
		} else {
			System.out.println("Debe tener categoria ");
		}
		
		//ejbProductosFacade.create(new Productos(this.nombreProducto, this.precioProducto, this.stockProducto, this.catProd));
		this.list = ejbProductosFacade.findAll();
		return null;
	}
	
	
	
	public String delete(Productos productos) {
		ejbProductosFacade.remove(productos);
		list = ejbProductosFacade.findAll();
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

	public List<Productos> getList() {
		return list;
	}

	public void setList(List<Productos> list) {
		this.list = list;
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

	public int getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}

	public Categoria getCatProd() {
		return catProd;
	}

	public void setCatProd(Categoria catProd) {
		this.catProd = catProd;
	}
	
	 public void newProducto() {
	        this.nombreProducto = null;
	        this.precioProducto = 0.0;
	        this.stockProducto = 0;
	    }
}
