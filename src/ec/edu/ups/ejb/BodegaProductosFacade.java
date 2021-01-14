package ec.edu.ups.ejb;

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
	
	public List<Object[]> listaProductosPorBodega(int idBodega){
		List<Object[]> elist=null;
		try {
			Query query = em.createNativeQuery("SELECT * from bodegaproductos where BODEGA_IDBODEGA =" + idBodega);
			elist = query.getResultList();
			System.out.println("Print ELIST"+elist);
		}catch(Exception e) {
			System.out.println("Impresion de Objetos");
		}
		//System.out.println("Lista de los Productos que no estan en Bodega"+listIdProductos);
		return elist;
	}
	
	
}