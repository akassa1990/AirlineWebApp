package edu.mum.cs545.ws;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@Path("flight")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlightRest {

	@Inject
	private FlightService flightService;
	@Inject
	private AirplaneService airplaneService;
	@Inject
	private AirportService airporteService;
	@Inject
	private AirlineService airlineService;


	@Path("list")
	@GET
	public List<Flight> getAllFlights() {
		List<Flight>flights = flightService.findAll();
		return flights;
	}
	
	@Path("depatures")
	@GET
	public List<Flight> findByDeparture(@DefaultValue("00/00/00 00:00:00") @QueryParam("date") String dateTime) {
		Date datetime;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		List<Flight> flights = new ArrayList<Flight>();
		
		try {
			datetime = df.parse(dateTime);
			flights = flightService.findByDeparture(datetime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flights;
	}
	
	@Path("arrivals")
	@GET
	public List<Flight> findByArrival(@DefaultValue("00/00/00 00:00:00") @QueryParam("date") String dateTime) {
		Date datetime;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		List<Flight> flights = new ArrayList<Flight>();
		
		try {
			datetime = df.parse(dateTime);
			flights = flightService.findByArrival(datetime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flights;
	}
	
	@Path("airplane")
	@GET
	public List<Flight> findByAirplane(@QueryParam("code") String code) {
		Airplane airplane = airplaneService.findBySrlnr(code);
		return (airplane != null) ? flightService.findByAirplane(airplane) : new ArrayList<Flight>();
	}
	
	@Path("number")
	@GET
	public List<Flight> findByNumber(@QueryParam("no") String no) {
		return flightService.findByNumber(no);
	}

	@Path("airline")
	@GET
	public List<Flight> findByAirline(@QueryParam("name") String name) {
		Airline airline = airlineService.findByName(name);
		return (airline != null) ? flightService.findByAirline(airline) : new ArrayList<Flight>();
	}
	
	@Path("origin")
	@GET
	public List<Flight> findByOrigin(@QueryParam("code") String code) {
		Airport airport = airporteService.findByCode(code);
		return (airport != null) ? flightService.findByOrigin(airport) : new ArrayList<Flight>();
	}
	
	@Path("destination")
	@GET
	public List<Flight> findByDestination(@QueryParam("code") String code) {
		Airport airport = airporteService.findByCode(code);
		return (airport != null) ? flightService.findByDestination(airport) : new ArrayList<Flight>();
	}
	
	@Path("new")
	@POST
	public void create(Flight flight) {
		flightService.create(flight);
	}
	
	@Path("edit")
	@PUT
	public Flight update(Flight flight) {
		Flight flight2 = flightService.find(flight);
		if(flight2 != null) flightService.update(flight);
		return flight2;
	}
	
	@Path("delete")
	@DELETE
	public void delete(Flight flight) {
		Flight flight2 = flightService.find(flight);
		if(flight2 != null) flightService.delete(flight2);
	}
	
	
	
	/*@Path("depature/between")
	@GET
	public List<Flight> findByDepartureBetween(@DefaultValue("00/00/00") @QueryParam("from") String from, @DefaultValue("00/00/00") @QueryParam("to") String to) {
		
		Date dateFrom, dateTo;
		//DateFormat df = new SimpleDateFormat("MM/dd/YY");
		//DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		List<Flight>flights = new ArrayList<Flight>();
		
		try {
			dateFrom = df.parse(from);
			dateTo = df.parse(to);
			System.out.print(dateFrom.toString());
			System.out.print(dateTo.toString());
			flights.addAll(flightService.findByArrivalBetween(dateFrom, dateTo));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flights;
	}*/
	
}
