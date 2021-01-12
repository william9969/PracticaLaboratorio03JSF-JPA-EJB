package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		String queryFindPRoductos="SELECT p FROM Persona p WHERE u.cedula=:ced";
		try {
			persona = (Persona) em.createQuery(queryFindPRoductos).setParameter("ced",cedula ).getSingleResult();
			em.close();
		}catch(Exception e) {
			System.out.println("Error al buscar la Persona");
		}
		
		return persona;
	}
	

}
