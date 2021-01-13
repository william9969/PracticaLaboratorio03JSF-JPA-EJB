package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Bodega;

@Stateless
public class BodegaFacade extends AbstractFacade<Bodega>{

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;

	
	public BodegaFacade() {
		super(Bodega.class);
	}


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	public Bodega buscarBodega (String nombre) {
		try {
			String jpql = "SELECT b FROM Bodega b WHERE b.nombre='" + nombre + "'";
			Bodega bod = (Bodega) em.createQuery(jpql).getSingleResult();
			return bod;
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
