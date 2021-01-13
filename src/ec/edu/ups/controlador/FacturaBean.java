package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaProductosFacade;
import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.BodegaProductos;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Productos;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named(value = "nuevaFacturaBean")
@SessionScoped

public class FacturaBean implements Serializable {

	private static final long serialVersionUID = 1;
	
	@EJB
	private PersonaFacade ejbPersonaFacade;
	@EJB
	private ProductosFacade ejbProductosFacade;
	@EJB
	private FacturaCabeceraFacade ejbCabeceraFacade;
	@EJB
	private BodegaProductosFacade ejbpBodegaProductosFacade;
	private String cedula;
	private String proNombre;
	private Persona persona;
	private List<FacturaDetalle> facturaDetalles;
	private List<Productos> productos;
	private Productos producto;
	private FacturaCabecera facturaCabecera;
	private List<BodegaProductos> inventarios;
	private BodegaProductos inventario;
	
	public FacturaBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void init() {
		facturaDetalles = new ArrayList<>();
		facturaCabecera =  new FacturaCabecera();
	}

	public PersonaFacade getEjbPersonaFacade() {
		return ejbPersonaFacade;
	}

	public void setEjbPersonaFacade(PersonaFacade ejbPersonaFacade) {
		this.ejbPersonaFacade = ejbPersonaFacade;
	}

	public ProductosFacade getEjbProductosFacade() {
		return ejbProductosFacade;
	}

	public void setEjbProductosFacade(ProductosFacade ejbProductosFacade) {
		this.ejbProductosFacade = ejbProductosFacade;
	}

	public FacturaCabeceraFacade getEjbCabeceraFacade() {
		return ejbCabeceraFacade;
	}

	public void setEjbCabeceraFacade(FacturaCabeceraFacade ejbCabeceraFacade) {
		this.ejbCabeceraFacade = ejbCabeceraFacade;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getProNombre() {
		return proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<FacturaDetalle> getFacturaDetalles() {
		return facturaDetalles;
	}

	public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public FacturaCabecera getFacturaCabecera() {
		return facturaCabecera;
	}

	public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
		this.facturaCabecera = facturaCabecera;
	}
	
	public List<BodegaProductos> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<BodegaProductos> inventarios) {
		this.inventarios = inventarios;
	}

	public BodegaProductos getInventario() {
		return inventario;
	}

	public void setInventario(BodegaProductos inventario) {
		this.inventario = inventario;
	}

	/**
	 * Metodo Buscar Usuario
	 * */
	public void buscarPersona() {
		try {
			this.persona = ejbPersonaFacade.find(this.cedula);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void buscarProducto() {
		this.productos = ejbProductosFacade.finByName(this.proNombre);
	}
	
	public void limpiar() {
		this.productos = null;
		this.productos = new ArrayList<>();
	}
	
	public String addProducto(BodegaProductos inventario) {
		this.inventario = inventario;
		FacturaDetalle fd = new FacturaDetalle();
		fd.setCantidad(1);
		fd.setDetProducto(producto);
		if (this.facturaDetalles.contains(fd)) {
			System.out.println("Producto ya existe");
			this.addCantidad(facturaDetalles.get(facturaDetalles.indexOf(fd)));
				
			
		}else {
			this.facturaDetalles.add(fd);
		}
		this.facturaCabecera.setListaFacturaDetalle(this.facturaDetalles);
		this.facturaCabecera.calcularSubtotal();
		this.facturaCabecera.calcularTotal();
		return null;
	}

	public void addCantidad(FacturaDetalle fd) {
		System.out.println("// Cantidad "+fd.getCantidad());
		if (this.inventario.getStock() > fd.getCantidad()) {
			fd.setCantidad(fd.getCantidad() + 1);
			this.facturaCabecera.setListaFacturaDetalle(this.facturaDetalles);
			this.facturaCabecera.calcularSubtotal();
			this.facturaCabecera.calcularTotal();
		}
	}
	
	public void deleteCantidad(FacturaDetalle fd) {
		System.out.println("Cantidad eliminada .. "+fd.getCantidad());
		if (fd.getCantidad() -1  ==0) {
			this.facturaDetalles.remove(fd);
		}else {
			fd.setCantidad(fd.getCantidad() - 1);
		}
		this.facturaCabecera.setListaFacturaDetalle(this.facturaDetalles);
		this.facturaCabecera.calcularSubtotal();
		this.facturaCabecera.calcularTotal();
	}
	
	public void deleteProducto(FacturaDetalle fd) {
		if (facturaDetalles.contains(fd)) {
			facturaDetalles.remove(fd);
		}
		this.facturaCabecera.setListaFacturaDetalle(this.facturaDetalles);
		this.facturaCabecera.calcularSubtotal();
		this.facturaCabecera.calcularTotal();
	}
	
	public String generarFactura() {
		if (persona !=null && facturaDetalles.size() > 0) {
			this.facturaCabecera.setPersonaFacturaCabecera(this.persona);
			this.facturaCabecera.calcularSubtotal();
			this.facturaCabecera.calcularTotal();
			
			try {
				for (FacturaDetalle fd : this.facturaCabecera.getListaFacturaDetalle()) {
					fd.setFacturaDetalleCabecera(facturaCabecera);
					for (BodegaProductos inv : fd.getDetProducto().getListBodegaProductos()) {
						inv.setStock(inv.getStock() - fd.getCantidad());
						ejbpBodegaProductosFacade.edit(inv);
					}
					ejbCabeceraFacade.create(this.facturaCabecera);
				}
			}catch (Exception e) {
				
			}
			facturaCabecera = new FacturaCabecera();
			persona = null;
			facturaDetalles = new ArrayList<>();
		}
		return null;
	}
	
	public void cancelarFactura() {
		facturaCabecera = new FacturaCabecera();
		persona = null;
		facturaDetalles = new ArrayList<>();
		productos = null;
	}
}
