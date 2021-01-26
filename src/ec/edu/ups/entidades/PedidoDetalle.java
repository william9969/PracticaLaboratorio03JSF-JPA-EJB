package ec.edu.ups.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoDetalle
 *
 */
@Entity

public class PedidoDetalle implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int cantidad;
	
	@ManyToOne
	@JoinColumn
	private Productos productos;
	
	@ManyToOne
	@JoinColumn
	private PedidoCabecera pedidoCabecera;
	public PedidoDetalle() {
		super();
	}
	public PedidoDetalle(int id, int cantidad, Productos productos, PedidoCabecera pedidoCabecera) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.productos = productos;
		this.pedidoCabecera = pedidoCabecera;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Productos getProductos() {
		return productos;
	}
	public void setProductos(Productos productos) {
		this.productos = productos;
	}
	public PedidoCabecera getPedidoCabecera() {
		return pedidoCabecera;
	}
	public void setPedidoCabecera(PedidoCabecera pedidoCabecera) {
		this.pedidoCabecera = pedidoCabecera;
	}
	
}
