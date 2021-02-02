package ec.edu.ups.entidades;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import java.util.Map;

/**
 * Entity implementation class for Entity: PedidoCabecera
 *
 */
@Entity

public class PedidoCabecera implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String estadoA;
	private String estadoS;
	
	@JsonbTransient
	@ManyToOne
	@JoinColumn
	private Persona persona;
	
	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoCabecera")
	private List<PedidoDetalle> pedidoDetalle = new ArrayList<PedidoDetalle>();
	
	public PedidoCabecera() {
		super();
	}

	public PedidoCabecera(int id, String estadoA, String estadoS, Persona persona) {
		super();
		this.id = id;
		this.estadoA = estadoA;
		this.estadoS = estadoS;
		this.persona = persona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstadoA() {
		return estadoA;
	}

	public void setEstadoA(String estadoA) {
		this.estadoA = estadoA;
	}

	public String getEstadoS() {
		return estadoS;
	}

	public void setEstadoS(String estadoS) {
		this.estadoS = estadoS;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<PedidoDetalle> getPedidoDetalle() {
		return pedidoDetalle;
	}

	public void setPedidoDetalle(List<PedidoDetalle> pedidoDetalle) {
		this.pedidoDetalle = pedidoDetalle;
	}

	public void crearPedidoDetalle(int cantidad, Productos productos) {
		if (this.pedidoDetalle == null) {
			pedidoDetalle =  new ArrayList<>();
		}
		PedidoDetalle pedidoDetalle = new PedidoDetalle(0,cantidad,productos,this);
		this.pedidoDetalle.add(pedidoDetalle);
	}

	
}
