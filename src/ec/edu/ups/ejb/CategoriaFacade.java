package ec.edu.ups.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Productos;

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
	
	public List<Integer> idBodega(int id){
		Query query = em.createNativeQuery("SELECT PRODUCTOS_IDPRODCUTO from  bodegaproductos where BODEGA_IDBODEGA =" + id);
        List<Integer> listIdProductos = query.getResultList();
        System.out.println("Lista de los Productos en Bodega"+listIdProductos);
		return listIdProductos;
	}
	
	public List<Categoria> buscarCategoriaPorBodega(int idBodega){
		List<Integer> lisIdprod = idBodega(idBodega);
		List<Categoria> listCategorias = new ArrayList<Categoria>();
		for (int i=0; i<lisIdprod.size(); i++) {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	        CriteriaQuery<Productos> productosCriteriaQuery = criteriaBuilder.createQuery(Productos.class);
	        Root<Productos> productosRoot = productosCriteriaQuery.from(Productos.class);
	        Predicate predicate= criteriaBuilder.equal(productosRoot.get("idProdcuto"),lisIdprod.get(i));
			productosCriteriaQuery.select(productosRoot).where(predicate);
			Productos producto=(Productos) em.createQuery(productosCriteriaQuery).getSingleResult(); 	
			System.out.println("Producto --->"+producto.getCatProd());
			listCategorias.add(producto.getCatProd());
			//productos.add(producto);
		}
		listCategorias=buscarCategoria(listCategorias);
		
		return listCategorias;
	}
	
	public List<Categoria> buscarCategoria(List<Categoria> listCategoria){
		List<Categoria> nueva=new ArrayList<Categoria>();;
		
		 Map<Integer, Categoria> mapCategorias = new HashMap<Integer, Categoria>(listCategoria.size());
		 //Aquí está la magia
		 for(Categoria c : listCategoria) {
			 mapCategorias.put(c.getIdCategoria(),c);
		 }
		 
		 //Agrego cada elemento del map a una nueva lista y muestro cada elemento.
		 System.out.println("Lista sin repetidos:");
		 for(Entry<Integer, Categoria> p : mapCategorias.entrySet()) {
			 nueva.add(p.getValue());
			 System.out.println(p.getValue());
		 }
		 	System.out.println("---------------------");
		 	System.out.println("nuevas Categorias"+ nueva);
		 	return nueva;
		 }
	
	
	
}
