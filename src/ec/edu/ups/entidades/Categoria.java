package ec.edu.ups.entidades;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import ec.edu.ups.entidades.Productos;

/**
 * Entity implementation class for Entity: Categoria
 *
 */

@Entity
public class Categoria implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria;
	private String nombre;
	
	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "catProd")
	private Set<Productos> productos = new HashSet<Productos>();
	
	@JsonbTransient
	@Transient 
	private boolean editable;
	
	public Categoria() {
		
	}

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
		
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		result = prime * result + (editable ? 1231 : 1237);
		result = prime * result + idCategoria;
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
		Categoria other = (Categoria) obj;
		if (editable != other.editable)
			return false;
		
		if (idCategoria != other.idCategoria)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombre=" + nombre + ", editable="
				+ editable + "]";
	}
    
}
