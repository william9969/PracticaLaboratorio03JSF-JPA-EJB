package ec.edu.ups.rest;

import javax.ejb.EJB;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
		System.out.println("Este es el numero de Cedula"+ cedula);
		System.out.println("He llegado con JSON");
		Jsonb  jsonb = JsonbBuilder.create();
		Persona persona = ejebPersonaFacade.buscarPersonaPorCedula(cedula);
		System.out.println("Objeto Recuperad"+persona);
		System.out.println("El nombre es"+persona.getNombres());
		return Response.status(200).entity(persona).build();
	}

}
