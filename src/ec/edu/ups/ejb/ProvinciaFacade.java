package ec.edu.ups.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Provincia;
@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia> {

	@PersistenceContext(unitName = "Practica3")
	private EntityManager em;

	public ProvinciaFacade() {
		super(Provincia.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}