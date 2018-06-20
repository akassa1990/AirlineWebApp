package edu.mum.cs545.ws;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.FlightService;

@Named
@Path("airplane")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AirplaneRest {

	@Inject
	private FlightService flightService;
	@Inject
	private AirplaneService airplaneService;
	@Path("list")
	@GET
	public List<Airplane> getAllAirplanes() {
		List<Airplane> airplanes = airplaneService.findAll();
		return airplanes;
	}
	
	@Path("model")
	@GET
	public List<Airplane> findByModel(@DefaultValue("") @QueryParam("model") String model) {
		List<Airplane> airplanes = airplaneService.findByModel(model);
		return airplanes;
	}
	
	@Path("serial")
	@GET
	public Airplane findBySerial(@QueryParam("code") String code) {
		Airplane airplanes = airplaneService.findBySrlnr(code);
		return airplanes;
	}
	
	@Path("flight")
	@GET
	public List<Airplane> findByFlight(Flight flight) {
		Flight flight2 = (flight != null) ? flightService.find(flight) : flight;
		return (flight2 != null) ? airplaneService.findByFlight(flight2) : new ArrayList<Airplane>();
	}
	
	@Path("new")
	@POST
	public void create(Airplane airplane) {
		if(airplane != null) airplaneService.create(airplane);
	}
	
	@Path("edit")
	@PUT
	public Airplane update(Airplane airplane) {
		Airplane airplane2 = (airplane != null) ? airplaneService.find(airplane) : airplane;
		if(airplane2 != null) airplaneService.update(airplane2);
		return airplane2;
	}
	
	@Path("delete")
	@POST
	public void delete(Airplane airplane) {
		Airplane airplane2 = (airplane != null) ? airplaneService.find(airplane) : airplane;
		if(airplane2 != null) airplaneService.delete(airplane2);
	}
	
	
}
