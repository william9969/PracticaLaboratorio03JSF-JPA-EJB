package ec.edu.ups.controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidades.Persona;

@Named
@RequestScoped
public class RegistrarClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonaFacade ejePersonaFacade;
	
	private String cedula= "";
	private String cedulaBuscar= "";
	private String nombres= "";
	private String direccion= "";
	private String correo= "";
	private String contrasenia= "";
	
	private String visibilidad="display: none";
	
	public void buscarPersona() {
		try {
			Persona persona = ejePersonaFacade.buscarPersonaPorCedula(cedulaBuscar);
			this.setCedula(persona.getCedula());
			this.setNombres(persona.getNombres());
			this.setDireccion(persona.getDireccion());
			this.setCorreo(persona.getCorreo());
			this.setVisibilidad("display: inline");
			System.out.println("Encontro   "+cedulaBuscar);
			System.out.println("Nombres  "+ nombres);
		} catch (Exception e) {
			System.out.println("ERROR AL BUSCAR PERSONA...");
		}
	}

	public String editarCliente() {
		try {
			Persona persona = ejePersonaFacade.buscarPersonaPorCedula(cedula);
			
			persona.setContrasenia(contrasenia);
			
			ejePersonaFacade.edit(persona);
			
			visibilidad="display: none";
			
			return "/principal.xhtml";
			
		} catch (Exception e) {
			System.out.println("No se puede editar ...");
			return "/RegistrarCliente.xhtml";
		}
	}
	public PersonaFacade getEjePersonaFacade() {
		return ejePersonaFacade;
	}

	public void setEjePersonaFacade(PersonaFacade ejePersonaFacade) {
		this.ejePersonaFacade = ejePersonaFacade;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedulaBuscar() {
		return cedulaBuscar;
	}

	public void setCedulaBuscar(String cedulaBuscar) {
		this.cedulaBuscar = cedulaBuscar;
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

	public String getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}
}
