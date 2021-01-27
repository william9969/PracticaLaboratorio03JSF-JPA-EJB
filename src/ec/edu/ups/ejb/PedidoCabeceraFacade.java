package ec.edu.ups.ejb;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import ec.edu.ups.entidades.PedidoCabecera;

@Stateless
public class PedidoCabeceraFacade extends AbstractFacade<PedidoCabecera>{

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;

	
	public PedidoCabeceraFacade() {
		super(PedidoCabecera.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public List<PedidoCabecera> buscarPorClienteyCedula(String cedula){
		try {
			String query = "SELECT pc FROM PedidoCabecera pc Where pc.Persona.cedula ='"+cedula+"'";
			List<PedidoCabecera> pedidoCabeceras = em.createQuery(query).getResultList();
					return pedidoCabeceras;
		}
		catch (Exception e) {
			return null;
		}
	}
}
