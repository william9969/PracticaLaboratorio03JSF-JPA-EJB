package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import ec.edu.ups.controlador.*;
import ec.edu.ups.entidades.*;
import ec.edu.ups.ejb.*;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class GestionEmpresaBean implements Serializable{
	
	@EJB
	ProductosFacade ejbProductosFacade;
	@EJB
	BodegaFacade ejbBodegaFacade;
	@EJB
	BodegaProductosFacade ejbBodegaProductosFacade;
	
	private List<Productos> listProductosTotal;
	private List<Bodega> listBodegasTotal;
	private int stockTotal;
	
	public List<Productos> getListProductosTotal() {
		return listProductosTotal;
	}

	public void setListProductosTotal(List<Productos> listProductosTotal) {
		this.listProductosTotal = listProductosTotal;
	}

	public List<Bodega> getListBodegasTotal() {
		return listBodegasTotal;
	}

	public void setListBodegasTotal(List<Bodega> listBodegasTotal) {
		this.listBodegasTotal = listBodegasTotal;
	}

	public int getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}
	@Init
	public void init() {
		listProductosTotal=ejbProductosFacade.findAll();
		for(int i=0;i<listProductosTotal.size();i++) {
			int stockT=ejbBodegaProductosFacade.stockTotalProducto(listProductosTotal.get(i).getIdProdcuto());
			listProductosTotal.get(i).setStock(stockT);
		}
		listBodegasTotal=ejbBodegaFacade.findAll();
	}
	
}
