package edu.mum.cs545.ws;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("/airline")
public class AirlineRest {
    @Inject
    AirlineService airlineService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Airline> findAll(){
        return airlineService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Airline findByName(@PathParam("name") String name){
        return airlineService.findByName(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/flight")
    public List<Airline> findByFlight(Flight flight){
        return airlineService.findByFlight(flight);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Airline airline){
        airlineService.create(airline);
        return Response.status(Response.Status.OK).entity("Airline Created").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Airline airline){
        airlineService.update(airline);
        return Response.status(Response.Status.OK).entity("Airline Updated").build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Airline airline){
        Airline fetchedAirline = airlineService.find(airline);
        airlineService.delete(fetchedAirline);
        return Response.status(Response.Status.OK).entity("Airline Deleted").build();
    }
}
