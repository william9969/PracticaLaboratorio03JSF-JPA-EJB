package ec.edu.ups.entidades;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import ec.edu.ups.entidades.FacturaCabecera;

/**
 * Entity implementation class for Entity: FacturaDetalle
 *
 */
@Entity

public class FacturaDetalle implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFacturaDetalle;
	private int cantidad;
	private double total;
	
	@JsonbTransient
	@ManyToOne
	@JoinColumn
	private FacturaCabecera facturaDetalleCabecera;
	
	@JsonbTransient
	@ManyToOne
	@JoinColumn
	private Productos detProducto;
	
	@JsonbTransient
	@Transient
    private boolean editable;
	
	public FacturaDetalle() {
		super();
	}

	public FacturaDetalle(int cantidad, double total, FacturaCabecera facturaDetalleCabecera,
			Productos detProducto) {
		super();
		
		this.cantidad = cantidad;
		this.total = total;
		this.facturaDetalleCabecera = facturaDetalleCabecera;
		this.detProducto = detProducto;
	}

	public int getIdFacturaDetalle() {
		return idFacturaDetalle;
	}

	public void setIdFacturaDetalle(int idFacturaDetalle) {
		this.idFacturaDetalle = idFacturaDetalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public FacturaCabecera getFacturaDetalleCabecera() {
		return facturaDetalleCabecera;
	}

	public void setFacturaDetalleCabecera(FacturaCabecera facturaDetalleCabecera) {
		this.facturaDetalleCabecera = facturaDetalleCabecera;
	}

	public Productos getDetProducto() {
		return detProducto;
	}

	public void setDetProducto(Productos detProducto) {
		this.detProducto = detProducto;
	}
	
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((detProducto == null) ? 0 : detProducto.hashCode());
		result = prime * result + ((facturaDetalleCabecera == null) ? 0 : facturaDetalleCabecera.hashCode());
		result = prime * result + idFacturaDetalle;
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		FacturaDetalle other = (FacturaDetalle) obj;
		if (cantidad != other.cantidad)
			return false;
		if (detProducto == null) {
			if (other.detProducto != null)
				return false;
		} else if (!detProducto.equals(other.detProducto))
			return false;
		if (facturaDetalleCabecera == null) {
			if (other.facturaDetalleCabecera != null)
				return false;
		} else if (!facturaDetalleCabecera.equals(other.facturaDetalleCabecera))
			return false;
		if (idFacturaDetalle != other.idFacturaDetalle)
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		return true;
	}*/
   
	
}
