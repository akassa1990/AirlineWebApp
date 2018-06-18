package cs545.airline.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@ManagedBean(name="flightBean")
public class FlightBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Flight> allFlights;
	
	
	@Inject
	private FlightService flightService;
		
	public List<Flight> getAllFlights(){
		allFlights = flightService.findAll();
		System.out.println("Availabe Data: "+allFlights.size());
		return allFlights;
	}	

}
