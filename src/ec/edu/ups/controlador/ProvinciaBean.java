package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ProvinciaFacade;
import ec.edu.ups.entidades.Provincia;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProvinciaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProvinciaFacade ejbProvinciaFacade;
	
	private int idProvincia;
	private String nombre;
	
	private List<Provincia> list;
	
	public String add() {
		ejbProvinciaFacade.create(new Provincia(this.nombre));
		list = ejbProvinciaFacade.findAll();
		return null;
	}
	
	public String delete(Provincia provincia) {
		ejbProvinciaFacade.remove(provincia);
		list = ejbProvinciaFacade.findAll();
		return null;
	}
	
	public String edit(Provincia provincia) {
		provincia.setEditable(true);
		return null;
	}
	
	public String save(Provincia provincia) {
		ejbProvinciaFacade.edit(provincia);
		provincia.setEditable(false);
		return null;
	}
}
