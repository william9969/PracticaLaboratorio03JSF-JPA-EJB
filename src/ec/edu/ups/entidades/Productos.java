package ec.edu.ups.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entidades.Categoria;

/**
 * Entity implementation class for Entity: Productos
 *
 */
@Entity

public class Productos implements Serializable {

	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProdcuto;
	
	private String nombreProducto;
	private double precioProducto;
	private int stockProducto;
	//private char estado;
	
	@ManyToOne
	@JoinColumn
	private Categoria catProd;
	
	@ManyToMany(mappedBy = "listProductos")
	private List<Bodega> listBodegas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "detProducto")
	private List<FacturaDetalle> facturaDetalles;
	
	@Transient
	private boolean editable;
	
	public Productos() {
		
	}
	
	

	public Productos(String nombreProducto, double precioProducto, int stockProducto,
			Categoria catProd) {
		super();
		
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
		this.stockProducto = stockProducto;
		this.catProd = catProd;
		
		listBodegas =  new ArrayList<Bodega>();
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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Categoria getCatProd() {
		return catProd;
	}

	public void setCatProd(Categoria catProd) {
		this.catProd = catProd;
	}

	
	public List<Bodega> getListBodegas() {
		return listBodegas;
	}


	public void setListBodegas(List<Bodega> listBodegas) {
		this.listBodegas = listBodegas;
	}

	public void addBodegas(Bodega bodegas) {
		this.listBodegas.add(bodegas);
	}

	public List<FacturaDetalle> getFacturaDetalles() {
		return facturaDetalles;
	}

	public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

	public void addFacturaDetalle(FacturaDetalle facDetalles) {
		this.facturaDetalles.add(facDetalles);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catProd == null) ? 0 : catProd.hashCode());
		result = prime * result + (editable ? 1231 : 1237);
		result = prime * result + ((facturaDetalles == null) ? 0 : facturaDetalles.hashCode());
		result = prime * result + idProdcuto;
		result = prime * result + ((listBodegas == null) ? 0 : listBodegas.hashCode());
		result = prime * result + ((nombreProducto == null) ? 0 : nombreProducto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precioProducto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stockProducto;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Productos other = (Productos) obj;
		if (catProd == null) {
			if (other.catProd != null)
				return false;
		} else if (!catProd.equals(other.catProd))
			return false;
		if (editable != other.editable)
			return false;
		if (facturaDetalles == null) {
			if (other.facturaDetalles != null)
				return false;
		} else if (!facturaDetalles.equals(other.facturaDetalles))
			return false;
		if (idProdcuto != other.idProdcuto)
			return false;
		if (listBodegas == null) {
			if (other.listBodegas != null)
				return false;
		} else if (!listBodegas.equals(other.listBodegas))
			return false;
		if (nombreProducto == null) {
			if (other.nombreProducto != null)
				return false;
		} else if (!nombreProducto.equals(other.nombreProducto))
			return false;
		if (Double.doubleToLongBits(precioProducto) != Double.doubleToLongBits(other.precioProducto))
			return false;
		if (stockProducto != other.stockProducto)
			return false;
		return true;
	}

	

}
