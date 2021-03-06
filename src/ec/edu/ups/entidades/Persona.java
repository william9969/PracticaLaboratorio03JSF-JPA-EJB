package ec.edu.ups.entidades;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
//@NamedQuery(name = "getByCedula", query="SELECT c FROM Cliente c WHERE c.cedula =:cedula")

@Entity
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona;
	private String cedula;
	private String nombres;
	private String direccion;
	//@Column(name = "CORREO", unique = true)
	private String correo;
	
	private String contrasenia;
	private char rolUsuario; //Para rol de Usuario 'A' administrador, 'E' empleado, 'C' cliente
	private boolean activo;
	private char estado;
	@JsonbTransient
	@Transient
    private boolean editable;
	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personafacturaCabecera" )
	private Set<FacturaCabecera> facturas = new HashSet<FacturaCabecera>();
	
	
	
	
	
	public Persona(String cedula, String nombres, String direccion, String correo, String contrasenia,
			char rolUsuario, boolean activo, char estado) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.direccion = direccion;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.rolUsuario = rolUsuario;
		this.activo = activo;
		this.estado = estado;
	}

	public Persona() {
		this.rolUsuario = 'C';
		this.contrasenia = "cliente";
		this.activo = true;
	}

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public char getRolUsuario() {
		return rolUsuario;
	}
	public void setRolUsuario(char rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<FacturaCabecera> getFacturas() {
		return facturas;
	}
	public void setFacturas(Set<FacturaCabecera> facturas) {
		this.facturas = facturas;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		result = prime * result + rolUsuario;
		
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
		Persona other = (Persona) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		if (contrasenia == null) {
			if (other.contrasenia != null)
				return false;
		} else if (!contrasenia.equals(other.contrasenia))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		if (rolUsuario != other.rolUsuario)
			return false;
		
		return true;
	}
	
	
}




