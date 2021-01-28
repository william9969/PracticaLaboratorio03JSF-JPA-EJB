package ec.edu.ups.controlador;

import java.io.Serializable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaProductosFacade;
import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.BodegaProductos;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.PedidoCabecera;
import ec.edu.ups.entidades.PedidoDetalle;
import ec.edu.ups.entidades.Persona;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ListarPedidosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PedidoCabeceraFacade ejbPedidoCabeceraFacade;
	
	@EJB
	private ProductosFacade ejbProductosFacade;
	
	@EJB
	private FacturaCabeceraFacade ejbFacturaCabeceraFacade;
	
	@EJB
	private BodegaProductosFacade ejbpBodegaProductosFacade;
	
	@EJB
	private PersonaFacade ejbPersonaFacade;
	
	private int id;
	private String cedula;
	private Persona persona;
	
	private List<PedidoCabecera> pedidoCabeceras;
	private List<PedidoDetalle> pedidoDetalles = new ArrayList<PedidoDetalle>();
	

	private String nombres;
	private String direccion;
	private String correo;
	
	@PostConstruct
	public void init() {
		try {
			pedidoCabeceras = ejbPedidoCabeceraFacade.findAll();
		} catch (Exception e) {
			System.out.println("null   "+e);
		}
		
	}
	
	public void buscarPorCedula() {
		System.out.println(pedidoCabeceras);
		pedidoCabeceras = new ArrayList<PedidoCabecera>();
		try {
			pedidoCabeceras = ejbPedidoCabeceraFacade.buscarPorClienteyCedula(cedula);
		}catch (Exception e) {
			pedidoCabeceras = ejbPedidoCabeceraFacade.findAll();
					System.out.println("No tiene pedido cabecera...");
		}
	}
	
	public void mostrarDetalles(PedidoCabecera pedidoCabecera) {
		pedidoDetalles = new ArrayList<PedidoDetalle>();
		this.setPedidoDetalles(pedidoCabecera.getPedidoDetalle());
	}

	public String cambiarEstado(PedidoCabecera pedido) {
		try {
			switch (pedido.getEstadoA()) {
			case "Enviado":
				generarFactura(pedido);
				pedido.setEstadoA(pedido.getEstadoS());
				pedido.setEstadoS("Recibido");
				break;
			case "Recibido":
				pedido.setEstadoA(pedido.getEstadoS());
				pedido.setEstadoS("En proceso");
				break;
			case "En proceso":
				pedido.setEstadoA(pedido.getEstadoS());
				pedido.setEstadoS("En camino");
				break;
			case "En camino":
				pedido.setEstadoA(pedido.getEstadoS());
				pedido.setEstadoS("Entregado");
				break;
			case "Entregado":
				break;
			default:
				break;
			}
			ejbPedidoCabeceraFacade.edit(pedido);
			
			pedidoCabeceras = ejbPedidoCabeceraFacade.findAll();
			return "/Practica03EJB-JPA-JSF/private/empleado/ListPedidos.xhtml";
			
		}catch (Exception e) {
			pedidoCabeceras = new ArrayList<PedidoCabecera>();
			pedidoDetalles = new ArrayList<PedidoDetalle>();
			return "/Practica03EJB-JPA-JSF/private/empleado/empleadoJSF.xhtml";
		}
	}
		
	private void generarFactura(PedidoCabecera pedido) {
		FacturaCabecera fc = new FacturaCabecera();
		List<FacturaDetalle> fd = new ArrayList<FacturaDetalle>();
		
		fc.setIdFacturaCabecera(0);
		fc.setPersonaFacturaCabecera(pedido.getPersona());
		fc.setSubtotal(0);
		fc.setIva(0);
		fc.setTotal(0);
		
		DecimalFormatSymbols separador = new DecimalFormatSymbols();
		separador.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat("#.00",separador);
		
		for (int i=0; i<pedido.getPedidoDetalle().size(); i++) {
			FacturaDetalle det = new FacturaDetalle(pedido.getPedidoDetalle().get(i).getCantidad(), pedido.getPedidoDetalle().get(i).getCantidad()*pedido.getPedidoDetalle().get(i).getProductos().getPrecioProducto(),fc,pedido.getPedidoDetalle().get(i).getProductos());
			
			fc.setSubtotal(fc.getSubtotal()+pedido.getPedidoDetalle().get(i).getCantidad()*pedido.getPedidoDetalle().get(i).getProductos().getPrecioProducto());
			fc.setIva(Float.parseFloat(format.format(fc.getSubtotal()*(float)0.12)));
			fc.setTotal(Float.parseFloat(format.format(fc.getIva()+fc.getSubtotal())));
			fd.add(det);
			
			//Actualizar stock
			
			for (BodegaProductos inv : det.getDetProducto().getListBodegaProductos()) {
				inv.setStock(inv.getStock() - det.getCantidad());
				ejbpBodegaProductosFacade.edit(inv);
				
			}
		}
		
		ejbFacturaCabeceraFacade.create(fc);
		
		fc.setListaFacturaDetalle(fd);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public List<PedidoCabecera> getPedidoCabeceras() {
		return pedidoCabeceras;
	}

	public void setPedidoCabeceras(List<PedidoCabecera> pedidoCabeceras) {
		this.pedidoCabeceras = pedidoCabeceras;
	}

	public List<PedidoDetalle> getPedidoDetalles() {
		return pedidoDetalles;
	}

	public void setPedidoDetalles(List<PedidoDetalle> pedidoDetalles) {
		this.pedidoDetalles = pedidoDetalles;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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

	public PedidoCabeceraFacade getEjbPedidoCabeceraFacade() {
		return ejbPedidoCabeceraFacade;
	}

	public void setEjbPedidoCabeceraFacade(PedidoCabeceraFacade ejbPedidoCabeceraFacade) {
		this.ejbPedidoCabeceraFacade = ejbPedidoCabeceraFacade;
	}
	
}
