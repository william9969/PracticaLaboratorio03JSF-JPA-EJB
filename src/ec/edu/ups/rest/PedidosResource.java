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

import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductosFacade;
import ec.edu.ups.entidades.Bodega;
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

}