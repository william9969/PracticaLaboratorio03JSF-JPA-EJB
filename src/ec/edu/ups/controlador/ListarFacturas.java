package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import javax.validation.constraints.Past;

import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.Persona;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ListarFacturas implements Serializable {

	@EJB
	private FacturaDetalleFacade ejbFacturaDetalleFacade;
	@EJB
	private FacturaCabeceraFacade ejbFacturaCabeceraFacade;
	
	private int idFacturaCabecera;
	private double iva;
	private double subtotal;
	private double total;
	private List<FacturaCabecera> listFacturas;
	private FacturaCabecera fabCab;
	private Persona personafacturaCabecera;
	
	@PostConstruct
	public void init() {
		listFacturas = ejbFacturaCabeceraFacade.findAll();
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


	public FacturaCabecera getFabCab() {
		return fabCab;
	}


	public void setFabCab(FacturaCabecera fabCab) {
		this.fabCab = fabCab;
	}


	public List<FacturaCabecera> getListFacturas() {
		return listFacturas;
	}


	public void setListFacturas(List<FacturaCabecera> listFacturas) {
		this.listFacturas = listFacturas;
	}


	public Persona getPersonafacturaCabecera() {
		return personafacturaCabecera;
	}


	public void setPersonafacturaCabecera(Persona personafacturaCabecera) {
		this.personafacturaCabecera = personafacturaCabecera;
	}

	public String listadoFacString() {
		listFacturas = ejbFacturaCabeceraFacade.findAll();
		
		return "/ListFacturas.xhtml";
	}
	
	public String delete(FacturaCabecera facCab) {
		ejbFacturaCabeceraFacade.remove(facCab);
		listFacturas = ejbFacturaCabeceraFacade.findAll();
		return null;
	}
	public void buscarFactura() {
		System.out.println("Ent"+this.idFacturaCabecera);
		fabCab = ejbFacturaCabeceraFacade.find(idFacturaCabecera);
		this.setIdFacturaCabecera(fabCab.getIdFacturaCabecera());
		this.setSubtotal(fabCab.getSubtotal());
		this.setIva(fabCab.getIva());
		this.setTotal(fabCab.getTotal());
	}
}
