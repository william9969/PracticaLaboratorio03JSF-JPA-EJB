package ec.edu.ups.entidades;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: bodega_productos
 *
 */
@Entity
public class BodegaProductos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBodegaProductos;
	
	@JsonbTransient
    @ManyToOne
    @JoinColumn
    private Bodega bodega;
	
	@JsonbTransient
    @ManyToOne
    @JoinColumn
    private Productos productos;
	
    private int stock;
    
    @JsonbTransient
	@Transient
	private boolean editable;
    
    public BodegaProductos() {
    	super();
    }    
	public BodegaProductos(Bodega bodega, Productos productos, int stock) {
		super();
		this.bodega = bodega;
		this.productos = productos;
		this.stock = stock;
	}
	

	/*public int getIdBodegaProductos() {
		return idBodegaProductos;
	}
	public void setIdBodegaProductos(int idBodegaProductos) {
		this.idBodegaProductos = idBodegaProductos;
	}/*/
	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodega == null) ? 0 : bodega.hashCode());
		result = prime * result + idBodegaProductos;
		result = prime * result + ((productos == null) ? 0 : productos.hashCode());
		result = prime * result + stock;
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
		BodegaProductos other = (BodegaProductos) obj;
		if (bodega == null) {
			if (other.bodega != null)
				return false;
		} else if (!bodega.equals(other.bodega))
			return false;
		if (idBodegaProductos != other.idBodegaProductos)
			return false;
		if (productos == null) {
			if (other.productos != null)
				return false;
		} else if (!productos.equals(other.productos))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	} 
	
    
    
   
}