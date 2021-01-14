package ec.edu.ups.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Provincia;
@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia> {

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
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
	
	public Provincia buscarProvinciaPorNombre(String nombre) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Provincia> provinciaCriteriaQuery = criteriaBuilder.createQuery(Provincia.class);
        Root<Provincia> provinciaRoot = provinciaCriteriaQuery.from(Provincia.class);
        Predicate predicate= criteriaBuilder.equal(provinciaRoot.get("nombre"),nombre);
        provinciaCriteriaQuery.select(provinciaRoot).where(predicate);
		
        return em.createQuery(provinciaCriteriaQuery).getSingleResult();
	}
}