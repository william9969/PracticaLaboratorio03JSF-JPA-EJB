package ec.edu.ups.controlador;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.BodegaProductos;
import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProvinciaFacade;
import ec.edu.ups.ejb.BodegaProductosFacade;
import ec.edu.ups.entidades.Productos;
import ec.edu.ups.entidades.Provincia;
import ec.edu.ups.entidades.Categoria;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BodegaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BodegaFacade ejbBodegaFacade;
	@EJB
	ProvinciaFacade ejbProvinciaFacade;
	@EJB
	private CategoriaFacade ejbCategoriaFacade;
	@EJB
	private BodegaProductosFacade ejbBodegaProductosFacade;
	
	private String nombre;
	private String telefono;
	private String cuidad;
	private String direccion;
	private Provincia provincia;
	private String nombreProvincia;
	private List<Bodega> listBodega;
	private List<Productos> listProductos;
	private List<Provincia> listProvincias;
	
	@PostConstruct
	public void init() {
		listProvincias=ejbProvinciaFacade.findAll();
		listBodega=ejbBodegaFacade.findAll();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCuidad() {
		return cuidad;
	}
	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<Bodega> getListBodega() {
		return listBodega;
	}
	public void setListBodega(List<Bodega> listBodega) {
		this.listBodega = listBodega;
	}
	public List<Productos> getListProductos() {
		return listProductos;
	}
	public void setListProductos(List<Productos> listProductos) {
		this.listProductos = listProductos;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	public List<Provincia> getListProvincias() {
		return listProvincias;
	}
	public void setListProvincias(List<Provincia> listProvincias) {
		this.listProvincias = listProvincias;
	}

///////////////////////////////////////////////////////////////////////////
	public String addBodega() {
		provincia=ejbProvinciaFacade.buscarProvinciaPorNombre(this.nombreProvincia);
		ejbBodegaFacade.create(new Bodega(this.nombre, this.telefono, this.cuidad, this.direccion, this.provincia));
		listBodega=ejbBodegaFacade.findAll();
		provincia=null;
		nombre="";
		telefono="";
		cuidad="";
		direccion="";
		return null;
	}
	
	public String deleteBodega(Bodega b) {
		ejbBodegaFacade.remove(b);
		listBodega=ejbBodegaFacade.findAll();
		return null;
	}
	
	public String editBodega(Bodega b) {
		b.setEditable(true);
		return null;
	}
	public String saveBodega(Bodega b) {
		ejbBodegaFacade.edit(b);
		b.setEditable(false);
		listBodega=ejbBodegaFacade.findAll();
		return null;
	}
	


	
	
	
	/*
	public void agregarBodegas() {
		//ID=1
				Categoria cat1 = ejbCategoriaFacade.find(1);
				Productos pro1 =  new Productos("Crema", 4.75,  cat1);
				Productos pro2 =  new Productos("Pasta Dental", 3.25,  cat1);
				Productos pro3 =  new Productos("Shampoo para bebes", 5.10 ,  cat1);
				Productos pro4 =  new Productos("Jabon", 5.10 ,  cat1);
				
				//ID=2
				Categoria cat2 = ejbCategoriaFacade.find(2);
				Productos pro5 =  new Productos("Suavitel", 3.60 , cat2);
				Productos pro6 =  new Productos("Detergente", 5.25 , cat2);
				Productos pro7 =  new Productos("Jabon Lava Todo", 4.30 , cat2);
				
				//ID=3
				Categoria cat3 = ejbCategoriaFacade.find(3);
				Productos pro8 =  new Productos("mascarillas", 4.30 , cat3);
				Productos pro9 =  new Productos("guantes quirurjicos", 5.20 ,  cat3);
				
				//ID=4
				Categoria cat4 = ejbCategoriaFacade.find(4);
				Productos pro10 =  new Productos("Dispensador de Jabon Liquido",12.50 ,  cat4);
				Productos pro11 =  new Productos("Dispensador de Toallas para manos",19.50 , cat4);
				
				//ID=5
				Categoria cat5 = ejbCategoriaFacade.find(5);
				Productos pro12 =  new Productos("Escoba", 1.80 , cat5);
				Productos pro13 =  new Productos("Trapeador", 1.90 , cat5);
				
				ejbProductosFacade.create(pro1);
				ejbProductosFacade.create(pro2);
				ejbProductosFacade.create(pro3);
				ejbProductosFacade.create(pro4);
				ejbProductosFacade.create(pro5);
				ejbProductosFacade.create(pro6);
				ejbProductosFacade.create(pro7);
				ejbProductosFacade.create(pro8);
				ejbProductosFacade.create(pro9);
				ejbProductosFacade.create(pro10);
				ejbProductosFacade.create(pro11);
				ejbProductosFacade.create(pro12);
				ejbProductosFacade.create(pro13);
				
				Provincia prov1 = new Provincia("Azuay");
				Provincia prov2 = new Provincia("Pichincha");
				Provincia prov3 = new Provincia("Guayas");
				
				ejbProvinciaFacade.create(prov1);
				ejbProvinciaFacade.create(prov2);
				ejbProvinciaFacade.create(prov3);

				Bodega bodegaDis = new Bodega("Bodega Dismero", "2408321", "Cuenca", "Av. Americas",prov1 );
				Bodega bodegaEcu = new Bodega("Bodega Ecua", "8652133", "Quito", "Av. Amazonas", prov2);
				Bodega bodegaLim = new Bodega("Bodega LIMPIA", "8652133", "Guayaquil", "Av. Amazonas",prov3);
				
				
				
				
				
				ejbBodegaFacade.create(bodegaDis);
				ejbBodegaFacade.create(bodegaEcu);
				ejbBodegaFacade.create(bodegaLim);
				
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaDis, pro1, 20));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaDis, pro2, 25));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaDis, pro3, 12));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaDis, pro4, 30));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaEcu, pro5, 28));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaEcu, pro6, 67));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaEcu, pro7, 13));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaEcu, pro8, 25));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaEcu, pro9, 22));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaLim, pro10, 15));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaLim, pro11, 8));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaLim, pro12, 90));
				ejbBodegaProductosFacade.create(new BodegaProductos(bodegaLim, pro13, 8));
	}*/
}
