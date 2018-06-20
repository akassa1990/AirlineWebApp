package edu.mum.cs545.ws;

import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirportService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("/airport")
public class AirportRest {
    @Inject
    private AirportService airportService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")
    public Airport findById(@PathParam("id") int id){
        Airport airport = new Airport();
        airport.setId(id);
        return airportService.find(airport);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<Airport> findAll(){
        return airportService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public List<Airport> findByName(@PathParam("name") String name){
        return airportService.findByName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/city/{city}")
    public List<Airport> findByCity(@PathParam("city") String city){
        return airportService.findByCity(city);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/country/{country}")
    public List<Airport> findByCountry(@PathParam("country") String country){
        return airportService.findByCountry(country);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/code/{airportCode}")
    public Airport findByCode(@PathParam("airportCode") String airportCode){
        return airportService.findByCode(airportCode);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/arrival")
    public List<Airport> findByArrival(Flight arrivalFlight){
        return airportService.findByArrival(arrivalFlight);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/departure")
    public List<Airport> findByDeparture(Flight departureFlight){
        return airportService.findByArrival(departureFlight);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Airport airport){
        airportService.create(airport);
        return Response.status(Response.Status.OK).entity("Airport Created").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Airport airport){
        airportService.update(airport);
        return Response.status(Response.Status.OK).entity("Airport Updated").build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Airport airport){
        airportService.delete(airport);
        return Response.status(Response.Status.OK).entity("Airport Deleted").build();
    }
}
