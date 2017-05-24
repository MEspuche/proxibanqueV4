package com.huios.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.huios.dao.ClientParticulierRepository;
import com.huios.metier.ClientParticulier;

@Controller
@Path("/directeur")
public class WSService {

	@Autowired
	ClientParticulierRepository clientParticulierRepository;
	
	@GET
	@Path("/afficherTousLesClients")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientParticulier> listerMesClientsParticuliers()
	{
		return clientParticulierRepository.findAll();
	}

}
