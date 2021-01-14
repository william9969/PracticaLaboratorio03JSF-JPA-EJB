package ec.edu.ups.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.edu.ups.entidades.BodegaProductos;
import ec.edu.ups.entidades.Productos;

@Stateless
public class BodegaProductosFacade extends AbstractFacade<BodegaProductos>{



	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;

	
	public BodegaProductosFacade() {
		super(BodegaProductos.class);
	}


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	public int devolverStock(int idProducto,int idBodega) {
		Query query = em.createNativeQuery("SELECT STOCK from bodegaproductos where BODEGA_IDBODEGA =" + idBodega + " AND PRODUCTOS_IDPRODCUTO="+ idProducto);
        int stockProductos = Integer.parseInt(String.valueOf(query.getSingleResult()));
        System.out.println("Stock de Cada Producto"+stockProductos);

		return stockProductos;
	}
	
}