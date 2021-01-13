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

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.BodegaProductosFacade;
import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.BodegaProductos;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Productos;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named(value = "FacturaBean")
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
	private FacturaDetalleFacade ejbFacturaDetalleFacade;
	@EJB
	private BodegaProductosFacade ejbpBodegaProductosFacade;
	@EJB
	private BodegaFacade ejbBodegaFacade;
	
	private String cedula;
	private String nombres;
	private String direccion;
	private String correo;
	private String proNombre;
	private Persona persona;
	private List<FacturaDetalle> facturaDetalles;
	private List<Productos> productos = new ArrayList<Productos>();
	private Productos producto;
	private FacturaCabecera facturaCabecera;
	private List<BodegaProductos> inventarios;
	private BodegaProductos inventario;
	private String bodega="";
	private List<Bodega> listBodega = new ArrayList<Bodega>();
	private List<Bodega> bodegas = new ArrayList<Bodega>();
	private String productoBuscar="";
	
	public FacturaBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void init() {
		facturaDetalles = new ArrayList<>();
		facturaCabecera =  new FacturaCabecera();
		
		listBodega = ejbBodegaFacade.findAll();
		
		productos = ejbProductosFacade.findAll();
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

	public BodegaProductosFacade getEjbpBodegaProductosFacade() {
		return ejbpBodegaProductosFacade;
	}

	public void setEjbpBodegaProductosFacade(BodegaProductosFacade ejbpBodegaProductosFacade) {
		this.ejbpBodegaProductosFacade = ejbpBodegaProductosFacade;
	}

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	

	public List<Bodega> getListBodega() {
		return listBodega;
	}

	public void setListBodega(List<Bodega> listBodega) {
		this.listBodega = listBodega;
	}

	public String getProductoBuscar() {
		return productoBuscar;
	}

	public void setProductoBuscar(String productoBuscar) {
		this.productoBuscar = productoBuscar;
	}

	public List<Bodega> getBodegas() {
		return bodegas;
	}

	public void setBodegas(List<Bodega> bodegas) {
		this.bodegas = bodegas;
	}

	/**
	 * Metodo Buscar Usuario
	 * */
	public void buscarPersona() {
		System.out.println("Entr");
		persona = ejbPersonaFacade.buscarPersonaCedula(cedula);
		this.setCedula(persona.getCedula());
		this.setNombres(persona.getNombres());
		
		this.setDireccion(persona.getDireccion());
		this.setCorreo(persona.getCorreo());
		
	}
	/**
	 * Metodo Filtrar
	 * */
	public void filtrar() {
		if (inventarios.equals("")) {
			inventarios = ejbBodegaFacade.buscarBodega(bodega).getListBodegaProductos();
		}else {
			List<Productos> pr= ejbProductosFacade.finByName(productoBuscar);
			productos = new ArrayList<Productos>();
			for (int i=0; i< pr.size();i++) {
				if (pr.get(i).getListBodegaProductos().get(0).getBodega().getNombre().equals(bodega)) {
					productos.add(pr.get(i));
				}
			}
		}
	}
	public void buscarProducto() {
		//this.productos = ejbProductosFacade.finByName(this.proNombre);
		this.producto = ejbProductosFacade.buscarProductoPorNombre(this.proNombre);
	}
	
	public void limpiar() {
		this.productos = null;
		this.productos = new ArrayList<>();
	}
	
	public String addProducto(Productos inventario) {
		this.producto = inventario;
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
	
	public void agregarDetalle(Productos productoDetalle) {
		try {
			
			
			
			
			DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
			separadoresPersonalizados.setDecimalSeparator('.');
			DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
			
			
			Productos producto = ejbProductosFacade.find(productoDetalle.getIdProdcuto());
			if(producto.getStock()>=productoDetalle.getStock()) {
				FacturaDetalle det = new FacturaDetalle(productoDetalle.getStock(), producto.getPrecioProducto()*productoDetalle.getStock(), facturaCabecera, producto);
				
				facturaCabecera.setSubtotal(facturaCabecera.getSubtotal()+producto.getPrecioProducto()*productoDetalle.getStock());
				facturaCabecera.setIva(Float.parseFloat(formato1.format(facturaCabecera.getSubtotal()*(float)0.12)));
				facturaCabecera.setTotal(facturaCabecera.getIva()+facturaCabecera.getSubtotal());
				facturaDetalles.add(det);
			}else {
				System.out.println("no entra a la condicion");
			}
		} catch (Exception e) {
			System.out.println("Falta introducir un cliente");
		}
			
		
	}
}
