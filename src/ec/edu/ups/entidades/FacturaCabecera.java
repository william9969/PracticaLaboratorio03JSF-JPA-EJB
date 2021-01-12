package ec.edu.ups.entidades;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entidades.FacturaDetalle;

/**
 * Entity implementation class for Entity: FacturaCabecera
 *s
 */
@Entity

public class FacturaCabecera implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFacturaCabecera;
	private double iva;
	private double subtotal;
	private double total;
	@ManyToOne
	@JoinColumn
	private Persona facturaCabecera;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaDetalleCabecera")
	private List<FacturaDetalle> listaFacturaDetalle;
	
	public FacturaCabecera() {
		super();
	}
	
	
	
	public FacturaCabecera(double iva, double subtotal, double total, Persona facturaCabecera) {
		super();
	
		this.iva = iva;
		this.subtotal = subtotal;
		this.total = total;
		this.facturaCabecera = facturaCabecera;
		
	}



	public int getIdFacturaCabecera() {
		return idFacturaCabecera;
	}


	public void setIdFacturaCabecera(int idFacturaCabecera) {
		this.idFacturaCabecera = idFacturaCabecera;
	}


	public double getIva() {
		return iva;
	}


	public void setIva(double iva) {
		this.iva = iva;
	}


	public double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}

	
	public Persona getFacturaCabecera() {
		return facturaCabecera;
	}


	public void setFacturaCabecera(Persona facturaCabecera) {
		this.facturaCabecera = facturaCabecera;
	}


	public List<FacturaDetalle> getListaFacturaDetalle() {
		return listaFacturaDetalle;
	}


	public void setListaFacturaDetalle(List<FacturaDetalle> listaFacturaDetalle) {
		this.listaFacturaDetalle = listaFacturaDetalle;
	}


	@Override
	public String toString() {
		return "FacturaCabecera [idFacturaCabecera=" + idFacturaCabecera + ", iva=" + iva + ", subtotal=" + subtotal
				+ ", total=" + total + ", facturaCabecera=" + facturaCabecera + ", listaFacturaDetalle="
				+ listaFacturaDetalle + "]";
	}

}