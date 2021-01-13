package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	
}