package ec.edu.ups.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	
	/**
	 * Realizar Pedidos
	 **/
	@POST
	@Path("/realizarPedido")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postPedido(@FormParam("ClienteId") Integer ClienteId,
			@FormParam("productoId") List<Integer> productoId,
			@FormParam("cantidad") List<Integer> cantidad) {
		System.out.println("Entro a realizar pedido");
		try {
			Persona cliente = ejbPersonaFacade.find(ClienteId);
			
			if (cliente == null || cliente.getIdPersona() == 0 ) {
				return Response.status(404).entity("El usuario no existe" ).build();
			}
			
			if (productoId.size() != cantidad.size()) {
				return Response.status(405).entity("Cantidad debe ser la misa").build();	
			}
			int n = productoId.size();
			PedidoCabecera pc = new PedidoCabecera(0, "Enviado", "Recibido", cliente);
			System.out.println("Pedido ... "+pc);
			for (int i=0 ; i<n;i++) {
				Productos productos = ejbProductosFacade.find(productoId.get(i));
				if (productos == null || productos.getIdProdcuto() == 0) {
					return Response.status(404).entity("No se puedo realizar el pedido con los productos").build();
				}
				
				pc.crearPedidoDetalle(cantidad.get(i), productos);
			}
			
			if (pc.getPedidoDetalle() == null || pc.getPedidoDetalle().isEmpty()) {
				return Response.status(405).entity("No se puede crear el Pedido con productos vacios").build();
			}
			//pc.setPersona(cliente);
			ejbPedidoCabeceraFacade.create(pc);
			return Response.status(201).entity("Se creo el pedido ... ").build();
			
		} catch (Exception e) {
			return Response.status(404).entity("No se puede encontrar ...").build();
		}

	}
	
}