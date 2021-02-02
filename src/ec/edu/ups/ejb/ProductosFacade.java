package ec.edu.ups.ejb;


import java.util.ArrayList;
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
		List<Integer> idProductos= idBodega(id);
		List<Productos> productos = new ArrayList<Productos>();
		try{
			for(int i=0;i<idProductos.size();i++) {
				int idpord=idProductos.get(i);
				//System.out.println("Id del Producto"+idpord);
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		        CriteriaQuery<Productos> productosCriteriaQuery = criteriaBuilder.createQuery(Productos.class);
		        Root<Productos> productosRoot = productosCriteriaQuery.from(Productos.class);
		        Predicate predicate= criteriaBuilder.equal(productosRoot.get("idProdcuto"),idpord);
				productosCriteriaQuery.select(productosRoot).where(predicate);
				Productos producto=(Productos) em.createQuery(productosCriteriaQuery).getSingleResult(); 	
				System.out.println("Producto --->"+producto);
				productos.add(producto);
			}
			System.out.println("Total Productos"+productos);
			
		}catch (Exception e) {
			
		}
		
		return productos;
	}
	public List<Integer> idBodega(int id){
		Query query = em.createNativeQuery("SELECT PRODUCTOS_IDPRODCUTO from  bodegaproductos where BODEGA_IDBODEGA =" + id);
        List<Integer> listIdProductos = query.getResultList();
        System.out.println("Lista de los Productos en Bodega"+listIdProductos);
		return listIdProductos;
	}
	
	public List<Productos> buscarNoProductosBodega(int idBodega,List<Productos> lisTot){
		List<Integer> idConProductos=idBodega(idBodega);
		List<Productos> productos = new ArrayList<Productos>();
		System.out.println("Lista de Productos Totales"+lisTot.size());
		System.out.println("Lista de Productos en Bodega"+idConProductos.size());
		if(idConProductos.size()==0) {
			productos=lisTot;
			return productos;
		}
		else {
			for(int i=0;i<lisTot.size();i++) {
				for(int j=0;j<idConProductos.size();j++) {
					if(lisTot.get(i).getIdProdcuto()==idConProductos.get(j)) {
						System.out.println("Encontrado primer ID"+lisTot.get(i).getIdProdcuto()+"ID en BODEGA"+idConProductos.get(j) );
						break;
					}
					if(j==idConProductos.size()-1) {						
						productos.add(lisTot.get(i));
					}
				}
			}
		}
		System.out.println("Total Productos");
		return productos;
	}
	/*public List<Integer> lisProductosTotales(){
		Query query = em.createNativeQuery("SELECT IDPRODCUTO from productos");
        List<Integer> listIdProductos = query.getResultList();
        System.out.println("Lista de los Productos Totales para bodega"+listIdProductos);
		ret*urn listIdProductos;
	}*/
	public List<Productos> finByName(String nombre) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Productos> productoCriteriaQuery = criteriaBuilder.createQuery(Productos.class);
        Root<Productos> productoRoot = productoCriteriaQuery.from(Productos.class);
        Predicate predicate= criteriaBuilder.equal(productoRoot.get("nombreProducto"),nombre);
        productoCriteriaQuery.select(productoRoot).where(predicate);
		
        return (List<Productos>) em.createQuery(productoCriteriaQuery).getSingleResult();
		
		
	}
	
	public Productos buscarProductoPorNombre(String nombre) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Productos> productoCriteriaQuery = criteriaBuilder.createQuery(Productos.class);
        Root<Productos> productoRoot = productoCriteriaQuery.from(Productos.class);
        Predicate predicate= criteriaBuilder.equal(productoRoot.get("nombreProducto"),nombre);
        productoCriteriaQuery.select(productoRoot).where(predicate);
		
        return em.createQuery(productoCriteriaQuery).getSingleResult();
	}
	
}
