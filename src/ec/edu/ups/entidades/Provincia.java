package ec.edu.ups.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Provincia
 *
 */
@Entity

public class Provincia implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProvincia;
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia")
	private List<Bodega> bodegas = new ArrayList<Bodega>();
	@Transient
    private boolean editable;
	
	public Provincia() {
		super();
	}


	public Provincia(String nombre) {
		super();
		
		this.nombre = nombre;
	}


	public int getIdProvincia() {
		return idProvincia;
	}


	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public List<Bodega> getBodegas() {
		return bodegas;
	}


	public void setBodegas(List<Bodega> bodegas) {
		this.bodegas = bodegas;
	}
	
	public boolean isEditable() {
		return editable;
	}


	public void setEditable(boolean editable) {
		this.editable = editable;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProvincia;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Provincia other = (Provincia) obj;
		if (idProvincia != other.idProvincia)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
   
}
