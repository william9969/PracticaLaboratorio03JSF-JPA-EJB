package ec.edu.ups.rest;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.server.PathParam;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidades.Persona;

@Path("/cliente/")
public class PersonResource {

	@EJB
	private PersonaFacade ejebPersonaFacade;
	
	@GET
	@Path("/validarCliente/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("cedula") String cedula) {
		Jsonb  jsonb = JsonbBuilder.create();
		Persona persona = ejebPersonaFacade.buscarPersonaPorCedula(cedula);
		return Response.status(204).entity(persona).build();
	}

}
