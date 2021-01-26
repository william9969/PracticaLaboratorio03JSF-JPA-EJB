package ec.edu.ups.ejb;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.edu.ups.entidades.FacturaCabecera;

@Stateless
public class FacturaCabeceraFacade extends AbstractFacade<FacturaCabecera> {

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;

	public FacturaCabeceraFacade() {
		super(FacturaCabecera.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	public List<FacturaCabecera> facturasyPersonas (int idPersona, int idFacCab) {
		Query query = em.createNativeQuery("SELECT * FROM facturacabecera where IDFACTURACABECERA = "+ idFacCab +" AND PERSONAFACTURACABECERA_IDPERSONA="+idPersona);
		List<FacturaCabecera> ids = query.getResultList();
		return ids;
	}

}
