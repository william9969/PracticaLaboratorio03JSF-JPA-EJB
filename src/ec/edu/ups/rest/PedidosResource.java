package ec.edu.ups.rest;

import java.io.IOException;

import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.PedidoCabecera;
import ec.edu.ups.entidades.PedidoDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Productos;

@Path("/detalle/")
public class PedidosResource {

	@EJB
	private ProductosFacade ejbProductosFacade;
	
	@EJB
	private PersonaFacade ejbPersonaFacade;
	
	@EJB
	private PedidoCabeceraFacade ejbPedidoCabeceraFacade;

	@EJB
	private CategoriaFacade ejbCategoriaFacade;
	
	@GET
	@Path("/listarProducto/{idBodega}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProd(@PathParam("idBodega") Integer id) {
		Jsonb jsonb = JsonbBuilder.create();
		List<Productos> productos = ejbProductosFacade.buscarProductosBodega(id);
		return Response.ok().entity(jsonb.toJson(productos))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	/**
	 * Listar Productos por su categoria
	 * */
	@GET
	@Path("/listProductosPorCategorias/{idCat}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response lisProCat(@PathParam("idCat")Integer idCat) {
		Jsonb jsonb = JsonbBuilder.create();
		Categoria cat = ejbCategoriaFacade.find(idCat);
		List<Productos> proCat = ejbProductosFacade.buscarProductosCategoria(cat);
		return Response.ok().entity(jsonb.toJson(proCat))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	/**
	 * Listar productos organizados por categorias y bodega
	 * */
	
	@GET
	@Path("/lisCatporBodegas/{idBodega}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response lisBodCat(@PathParam("idBodega")Integer idBodega) {
		Jsonb jsonb = JsonbBuilder.create();
		List<Categoria> catPorBodega = ejbCategoriaFacade.buscarCategoriaPorBodega(idBodega);
		return Response.ok().entity(jsonb.toJson(catPorBodega))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	
		
	}
}