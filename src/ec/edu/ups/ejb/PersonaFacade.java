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

import ec.edu.ups.entidades.Persona;

@Stateless
public class PersonaFacade extends AbstractFacade<Persona>{

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;
	
	public PersonaFacade() {
		super(Persona.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	public Persona buscarPersonaCedula(String cedula) {
		
		Persona persona=null;
		String queryFindPRoductos="SELECT p FROM Persona p WHERE p.cedula=:ced";
		try {
			persona = (Persona) em.createQuery(queryFindPRoductos).setParameter("ced",cedula ).getSingleResult();
			em.close();
		}catch(Exception e) {
			System.out.println("Error al buscar la Persona");
		}
		
		return persona;
	}
	
	public Persona finByEmailAndPass(String correo, String contrasenia) {
		try {
			String sql = "From Persona p Where p.correo = ?1 AND p.contrasenia = ?2";
			Query query = em.createQuery(sql);
			query.setParameter(1, correo);
			query.setParameter(2, contrasenia);
			return (Persona) query.getSingleResult();
		} catch (Exception e) {
			 System.out.println("Error: "+e);
	            return null;
		}
	}

	public List<Persona> findClientes() {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Persona> categoriaCriteriaQuery = criteriaBuilder.createQuery(Persona.class);
        Root<Persona> categoriaRoot = categoriaCriteriaQuery.from(Persona.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("rolUsuario"),'C');
        categoriaCriteriaQuery.select(categoriaRoot).where(predicate);
		
        
        return em.createQuery(categoriaCriteriaQuery).getResultList();
	}

	public Persona buscarPersonaPorCedula(String cedula) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Persona> categoriaCriteriaQuery = criteriaBuilder.createQuery(Persona.class);
        Root<Persona> categoriaRoot = categoriaCriteriaQuery.from(Persona.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("cedula"),cedula);
        categoriaCriteriaQuery.select(categoriaRoot).where(predicate);
		
        System.out.printf("cedula",cedula);
        return em.createQuery(categoriaCriteriaQuery).getSingleResult();
        
}

}
