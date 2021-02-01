package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.PedidoCabecera;

@Stateless
public class PedidoDetalleFacade extends AbstractFacade<PedidoCabecera>{

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;
	
	public PedidoDetalleFacade() {
		super(PedidoCabecera.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
