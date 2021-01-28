package ec.edu.ups.entidades;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bodega
 *
 */
@Entity
public class Bodega implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBodega;
	private String nombre;
	private String telefono;
	private String cuidad;
	private String direccion;
	@JsonbTransient
	@Transient
	private boolean editable;
	
	@JsonbTransient
	@ManyToOne
	@JoinColumn
	private Provincia provincia;
	
	//@ManyToMany(cascade = CascadeType.ALL)
	//private List<Productos> listProductos;
	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bodega")
	private List<BodegaProductos> listBodegaProductos;
	
	
	public Bodega() {
		super();
	}

	public Bodega(String nombre, String telefono, String cuidad, String direccion, Provincia provincia
			) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.cuidad = cuidad;
		this.direccion = direccion;
		this.provincia = provincia;
		
	}
	
	public int getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(int idBodega) {
		this.idBodega = idBodega;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCuidad() {
		return cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<BodegaProductos> getListBodegaProductos() {
		return listBodegaProductos;
	}
	public void setListBodegaProductos(List<BodegaProductos> listBodegaProductos) {
		this.listBodegaProductos = listBodegaProductos;
	}
	

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuidad == null) ? 0 : cuidad.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + idBodega;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Bodega other = (Bodega) obj;
		if (cuidad == null) {
			if (other.cuidad != null)
				return false;
		} else if (!cuidad.equals(other.cuidad))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (idBodega != other.idBodega)
			return false;
		
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	
}
