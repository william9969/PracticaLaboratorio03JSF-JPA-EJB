package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Productos;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaDetalleBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private FacturaDetalleFacade ejbFacturaDetalleFacade;

	@EJB
	private FacturaCabeceraFacade ejbFacturaCabecera;

	@EJB
	private ProductosFacade ejbProductosFacade;
	private int idFacturaDetalle;
	private int cantidad;
	private double total;

	private FacturaCabecera facCab;
	private Productos producto;
	private Persona persona;
	private List<FacturaDetalle> list;
	//private List<Productos> productos;

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
	public List<FacturaDetalle> getList() {
		return list;
	}
	public void setList(List<FacturaDetalle> list) {
		this.list = list;
	}

	public FacturaCabecera getFacCab() {
		return facCab;
	}
	public void setFacCab(FacturaCabecera facCab) {
		this.facCab = facCab;
	}
	public Productos getProducto() {
		return producto;
	}
	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public FacturaDetalleFacade getEjbFacturaDetalleFacade() {
		return ejbFacturaDetalleFacade;
	}
	public void setEjbFacturaDetalleFacade(FacturaDetalleFacade ejbFacturaDetalleFacade) {
		this.ejbFacturaDetalleFacade = ejbFacturaDetalleFacade;
	}
	public FacturaCabeceraFacade getEjbFacturaCabecera() {
		return ejbFacturaCabecera;
	}
	public void setEjbFacturaCabecera(FacturaCabeceraFacade ejbFacturaCabecera) {
		this.ejbFacturaCabecera = ejbFacturaCabecera;
	}
	public ProductosFacade getEjbProductosFacade() {
		return ejbProductosFacade;
	}
	public void setEjbProductosFacade(ProductosFacade ejbProductosFacade) {
		this.ejbProductosFacade = ejbProductosFacade;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String add() {

		ejbFacturaDetalleFacade.create(new FacturaDetalle(this.cantidad,this.total,this.facCab, this.producto));
		list = ejbFacturaDetalleFacade.findAll();
		return null;
	}

	private String delete(FacturaDetalle facturaDetalle) {
		ejbFacturaDetalleFacade.remove(facturaDetalle);
		list = ejbFacturaDetalleFacade.findAll();

		return null;
	}

	public String edict(FacturaDetalle facturaDetalle) 
	{
		facturaDetalle.setEditable(true);
		return null;
	}

	private String save(FacturaDetalle facturaDetalle) {
		ejbFacturaDetalleFacade.edit(facturaDetalle);
		facturaDetalle.setEditable(false);
		return null;
	}

	@PostConstruct
	public void init() {

	}

}
