package ec.edu.ups.controlador;

import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.controlador.*;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped

public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonaFacade ejbPersonaFacade;

	private int idPersona;
	private String cedula;
	private String nombres;
	private String direccion;
	//@Column(name = "CORREO", unique = true)
	private String correo;
	//@Column(name = "USUARIO", unique = true)
	private Persona usuario;
	private String contrasenia;
	private char rolUsuario; //Para rol de Usuario 'A' administrador, 'E' empleado, 'C' cliente
	private boolean activo;
	HttpSession session;
	FacesContext context;
	private List<Persona> list;
	private List<Persona> listClientes;
	
	
	@PostConstruct
	public void init() {
	/*	ejbPersonaFacade.create(new Persona("0104568972", "William Ramiro Sinchi Morocho", "Arturo Cisneros", "wsinchi@est.ups.edu.ec",  "wsinchi123", 'A', true));
		ejbPersonaFacade.create(new Persona("0106835762", "Jessica Maribel Guncay Carchipulla", "Av. Ricardo Duran", "gjessica@est.ups.edu.ec", "gjessica23", 'E', true));
		ejbPersonaFacade.create(new Persona("0106835762", "Carmen Alexandra Bravo Valdiviezo", "Santa Isabel", "cbravo@est.ups.edu.ec", "cbravo23", 'C', true));
*/
		list = ejbPersonaFacade.findAll();
	}
	public String add() {
		ejbPersonaFacade.create(new Persona(this.cedula, this.nombres, this.direccion, this.correo, this.contrasenia="123", this.rolUsuario='C', this.activo=true));
		return null;
	}
	
	public String delete(Persona persona) {
		ejbPersonaFacade.remove(persona);
		list = ejbPersonaFacade.findAll();
		return null;
	}
	
	public String edit(Persona persona) {
		
		persona.setEditable(true);
		return null;
		
	}
	
	public String save(Persona persona) {
		ejbPersonaFacade.edit(persona);
		persona.setEditable(false);
		return null;
	}

	public PersonaFacade getEjbPersonaFacade() {
		return ejbPersonaFacade;
	}

	public void setEjbPersonaFacade(PersonaFacade ejbPersonaFacade) {
		this.ejbPersonaFacade = ejbPersonaFacade;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
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

	
	public Persona getUsuario() {
		return usuario;
	}

	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Persona> getList() {
		return list;
	}

	public void setList(List<Persona> list) {
		this.list = list;
	}
	
	
	
	public List<Persona> getListClientes() {
		return listClientes;
	}
	public void setListClientes(List<Persona> listClientes) {
		this.listClientes = listClientes;
	}
	/**
	 * Metodo Iniciar Seccion
	 * */
	public String login() {
		try {
            if (!correo.equals("") && !contrasenia.equals("")) {
            	usuario = ejbPersonaFacade.finByEmailAndPass(correo, contrasenia);
                if (usuario != null) {

                    if (usuario.isActivo()) {
                        //System.out.println("Usuario... " + usuario);
                    	 context = FacesContext.getCurrentInstance();
                    	 session = (HttpSession) context.getExternalContext().getSession(true);
                         session.setAttribute("userlog", usuario);
                        switch (usuario.getRolUsuario()) {
                            case 'A':
                                System.out.println("admin");
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariolog", usuario);
                                return "/private/admin/adminPrincipal.xhtml";
                            case 'E':
                                System.out.println("empleado");
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariolog", usuario);
                                return "/private/empleado/empleadoJSF.xhtml";
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Su cuenta ah sido desactivada contacte con un administrador"));
                    }

                } else{
                    System.out.println("Usuario no es correcto");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales incorrectas"));
                }

                System.out.println("Pass... " + contrasenia);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Todos los campos son obligatorios"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Interno", "Error! interno intente de nuevo"));
        }
        return "/login.xhtml";
	}

	public void listarClientes() {
		this.list = ejbPersonaFacade.findClientes();
		cedula = null;
	}
	
	public void cerrarSession() throws IOException {
       
        session.setAttribute("userlog", null);
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/Practica03EJB-JPA-JSF/principal.xhtml");
	
	}
}
