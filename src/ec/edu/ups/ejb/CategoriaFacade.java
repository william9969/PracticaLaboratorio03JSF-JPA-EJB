package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Categoria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;

	public CategoriaFacade() {
		super(Categoria.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public Categoria buscarCategoriaPorNombre(String nombre) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Categoria> categoriaCriteriaQuery = criteriaBuilder.createQuery(Categoria.class);
        Root<Categoria> categoriaRoot = categoriaCriteriaQuery.from(Categoria.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("nombre"),nombre);
        categoriaCriteriaQuery.select(categoriaRoot).where(predicate);
		
        return em.createQuery(categoriaCriteriaQuery).getSingleResult();
	}

}
