package ec.edu.ups.controlador;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProvinciaFacade;
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
	
	private String nombre;
	private String telefono;
	private String cuidad;
	private String direccion;
	private Provincia provincia;
	private List<Bodega> listBodega;
	private List<Productos> listProductos;
	
	@PostConstruct
	public void init() {
		/*//ID=1
		Categoria cat1 = ejbCategoriaFacade.find(1);
		Productos pro1 =  new Productos("Crema", 4.75, 10, cat1);
		Productos pro2 =  new Productos("Pasta Dental", 3.25, 10, cat1);
		Productos pro3 =  new Productos("Shampoo para bebes", 5.10 , 10, cat1);
		Productos pro4 =  new Productos("Jabon", 5.10 , 10, cat1);
		
		//ID=2
		Categoria cat2 = ejbCategoriaFacade.find(2);
		Productos pro5 =  new Productos("Suavitel", 3.60 , 5, cat2);
		Productos pro6 =  new Productos("Detergente", 5.25 , 8, cat2);
		Productos pro7 =  new Productos("Jabon Lava Todo", 4.30 , 4, cat2);
		
		//ID=3
		Categoria cat3 = ejbCategoriaFacade.find(3);
		Productos pro8 =  new Productos("mascarillas", 4.30 , 20, cat3);
		Productos pro9 =  new Productos("guantes quirurjicos", 5.20 , 20, cat3);
		
		//ID=4
		Categoria cat4 = ejbCategoriaFacade.find(4);
		Productos pro10 =  new Productos("Dispensador de Jabon Liquido",12.50 , 13, cat4);
		Productos pro11 =  new Productos("Dispensador de Toallas para manos",19.50 , 7, cat4);
		
		//ID=5
		Categoria cat5 = ejbCategoriaFacade.find(5);
		Productos pro12 =  new Productos("Escoba", 1.80 , 7, cat5);
		Productos pro13 =  new Productos("Trapeador", 1.90 , 7, cat5);
		
		/**
		 * Datos de la Provincia
		 
		
		Provincia prov1 = new Provincia(0, "Azuay");
		Provincia prov2 = new Provincia(0, "Pichincha");
		Provincia prov3 = new Provincia(0, "Guayas");
		
		ejbProvinciaFacade.create(prov1);
		ejbProvinciaFacade.create(prov2);
		ejbProvinciaFacade.create(prov3);
		
		/**
		 * Datos de Bodegas
		 
		Bodega bodegaDis = new Bodega("Bodega Dismero", "2408321", "Cuenca", "Av. Americas",prov1 );
		Bodega bodegaEcu = new Bodega("Bodega Ecua", "8652133", "Quito", "Av. Amazonas", prov2);
		Bodega bodegaLim = new Bodega("Bodega LIMPIA", "8652133", "Guayaquil", "Av. Amazonas",prov3);
		
		bodegaDis.addProductos(pro1);
		bodegaDis.addProductos(pro2);
		bodegaDis.addProductos(pro3);
		bodegaDis.addProductos(pro4);
		
		bodegaEcu.addProductos(pro5);
		bodegaEcu.addProductos(pro6);
		bodegaEcu.addProductos(pro7);
		bodegaEcu.addProductos(pro8);
		bodegaEcu.addProductos(pro9);
		
		bodegaLim.addProductos(pro10);
		bodegaLim.addProductos(pro11);
		bodegaLim.addProductos(pro12);
		bodegaLim.addProductos(pro13);
		
		ejbBodegaFacade.create(bodegaDis);
		ejbBodegaFacade.create(bodegaEcu);
		ejbBodegaFacade.create(bodegaLim);*/
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
	
	public String addBodega() {
		ejbBodegaFacade.create(new Bodega(this.nombre, this.telefono, this.cuidad, this.direccion, this.provincia));
		listBodega=ejbBodegaFacade.findAll();
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
	
	
	
}
