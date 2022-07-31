/**
 * In questa classe viene istanziato un oggetto di tipo DAO
 * i quali metodi hanno accesso allo strato di persistenza ed utilizzati
 * per creare l'API pubblica al quale accedere attraverso l'url
 * \    /rest/ewallet/nomeMetodo
 */
package com.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dao.ContoCorrenteDAO;
import com.dao.ContoCorrenteDAOImpl;
import com.data.ContoCorrente;
import com.data.Movimento;

@Path("/ewallet")
public class ContoCorrenteRest {

	private static ContoCorrenteDAO ccDAO = new ContoCorrenteDAOImpl();

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createContoCorrente(ContoCorrente cc) {
		String response = "Conto corrente già esistente";
		if (ccDAO.inserisciConto(cc)) {
			response = "Conto corrente aggiunto con successo";
		}
		return Response.status(201).entity(response).build();
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancellaContoCorrente(@QueryParam("iban") String iban) {
		String response = "Conto corrente non esistente";
		if (ccDAO.rimuoviConto(iban)) {
			response = "Conto corrente rimosso con successo";
		}
		return Response.status(201).entity(response).build();
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificaContoCorrente(ContoCorrente cc) {
		ContoCorrente output = null;
		if (cc != null) {
			output = ccDAO.modificaConto(cc);
		}
		return Response.status(201).entity(output).build();
	}

	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContoCorrente> getAll() {
		return ccDAO.getAllConti();
	}

	@GET
	@Path("/getbyiban")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCcByIban(@QueryParam("iban") String iban) {
		ContoCorrente cc = ccDAO.getContoByIban(iban);
		return Response.status(201).entity(cc).build();
	}

	@POST
	@Path("/prelievo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prelievo(Movimento movimento) {
		ContoCorrente cc = ccDAO.preleva(movimento);
		return Response.status(201).entity(cc).build();
	}

	@POST
	@Path("/versamento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response versamento(Movimento movimento) {
		ContoCorrente cc = ccDAO.versa(movimento);
		return Response.status(201).entity(cc).build();
	}

	@GET
	@Path("/listamovimenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimento> listaMovimenti(@QueryParam("iban") String iban) {
		return ccDAO.listaMovimenti(iban);
	}
}
