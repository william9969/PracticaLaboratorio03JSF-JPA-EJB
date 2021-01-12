package ec.edu.ups.ejb;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Predicate;

import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Productos;

@Stateless
public class ProductosFacade extends AbstractFacade<Productos>{

	@PersistenceContext(unitName = "Practica03EJB-JPA-JSF")
	private EntityManager em;

	public ProductosFacade() {
		super(Productos.class);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public List<Productos> buscarProductosCategoria(Categoria categoria) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Productos> productosCriteriaQuery = criteriaBuilder.createQuery(Productos.class);
        Root<Productos> productosRoot = productosCriteriaQuery.from(Productos.class);
        Predicate predicate= criteriaBuilder.equal(productosRoot.get("catProd"),categoria);
		productosCriteriaQuery.select(productosRoot).where(predicate);
        return em.createQuery(productosCriteriaQuery).getResultList();
		
		/*System.out.println("Query de Productos");
		 * Select p from productos p where p.ccatProd.idCategoria=:idCat
		String queryFindPRoductos="SELECT p FROM Productos p JOIN p.catProd c WHERE c.idCategoria=:idCat";
		List<Productos> productos= em.createQuery(queryFindPRoductos).setParameter("idCat",id).getResultList();
		em.close();		
		System.out.println(productos);
		return productos;*/
	}
	
	public List<Productos> buscarProductosBodega(int id) {
		String queryFindPRoductos="SELECT p FROM Productos p JOIN p.catProd c WHERE c.idCategoria=:idCa";
		List<Productos> productos= em.createQuery(queryFindPRoductos).setParameter("idCa",id ).getResultList();
		em.close();		
		return productos;
	}


}
