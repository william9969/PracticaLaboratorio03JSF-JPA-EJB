package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;


@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class FacturaCabeceraBean implements Serializable{
	
	@EJB
	FacturaCabeceraFacade ejbFacturaCabecera;
	@EJB
	PersonaFacade ejbPersonaFacade;
	
	private double iva;
	private double subtotal;
	private double total;
	private String cedulaPersona;
	List<FacturaCabecera> listFacturaCabecera;
	List<FacturaDetalle> listFacturaDetalle;
	
	@PostConstruct
	 public void init() {
		 
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
	
	public String addBodega() {
		Persona persona= ejbPersonaFacade.buscarPersonaCedula(cedulaPersona);
		ejbFacturaCabecera.create(new FacturaCabecera(this.iva,this.subtotal,this.total,persona));
		listFacturaCabecera=ejbFacturaCabecera.findAll();
		return null;
	}
	
	public String deleteBodega(FacturaCabecera fc) {
		ejbFacturaCabecera.remove(fc);
		listFacturaCabecera=ejbFacturaCabecera.findAll();
		return null;
	}
	
	public String saveBodega(FacturaCabecera fc) {
		ejbFacturaCabecera.edit(fc);
		listFacturaCabecera=ejbFacturaCabecera.findAll();
		return null;
	}
	
	
}
