package cs545.airline.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Init;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@ManagedBean(name = "airLineBean")
public class AirLineBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Airline> airlines;
	private List<Airline> filterAirlines;
	private String airlineName;
	private Airline selectedAirline;
	private boolean updateFlag;
	private boolean addFlag = true;

	@Inject
	private AirlineService airlineService;

	public void addNewAirLine() {
		String strMsg = "";
		try {
			System.out.println("Add new Air Line");
			Airline airline = new Airline();
			airline.setName(airlineName);
			airlineService.create(airline);
			strMsg = "The airline " + airlineName + " added successfuly";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, strMsg, " "));
			airlineName = "";
		} catch (Exception e) {
			e.printStackTrace();
			strMsg = "There is an error please try again later!";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg, " "));
		}

	}

	public void edit() {
		String strMsg = "";
		try {
			addFlag = true;
			updateFlag = false;
			System.out.println("update Air Line ID : " + selectedAirline.getId());
			airlineService.update(selectedAirline);
			strMsg = "The airline " + selectedAirline.getName() + " updated successfuly";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, strMsg, " "));
			airlineName = "";
		} catch (Exception e) {
			e.printStackTrace();
			strMsg = "There is an error please try again later!";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg, " "));
		}
	}

	public void cancelUpdate() {
		addFlag = true;
		updateFlag = false;
	}

	public void removeAirline() {
		String strMsg = "";
		try {
			addFlag = true;
			updateFlag = false;
			System.out.println("Remove  Air Line");
			airlineService.delete(selectedAirline);
			strMsg = "The airline " + selectedAirline.getName() + " deleted successfuly";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, strMsg, " "));
			airlineName = "";
		} catch (Exception e) {
			e.printStackTrace();
			strMsg = "There is an error please try again later!";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg, " "));
		}
	}

	public void getSelectedAirline(ActionEvent event) {
		updateFlag = true;
		addFlag = false;
		selectedAirline = (Airline) event.getComponent().getAttributes().get("airline");
		System.out.println("selected :: " + selectedAirline.getName() + " :: " + selectedAirline.getId());
	}

	public List<Airline> getAirlines() {
		airlines = airlineService.findAll();
		System.out.println("Availabe AirLines : " + airlines.size());
		return airlines;
	}

	public void setAirlines(List<Airline> airlines) {
		this.airlines = airlines;
	}

	public AirlineService getAirlineService() {
		return airlineService;
	}

	public void setAirlineService(AirlineService airlineService) {
		this.airlineService = airlineService;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public Airline getSelectedAirline() {
		return selectedAirline;
	}

	public void setSelectedAirline(Airline selectedAirline) {
		this.selectedAirline = selectedAirline;
	}

	public List<Airline> getFilterAirlines() {
		return filterAirlines;
	}

	public void setFilterAirlines(List<Airline> filterAirlines) {
		this.filterAirlines = filterAirlines;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public boolean isAddFlag() {
		return addFlag;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

}
