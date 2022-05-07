package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private Double pricePerDay;
	private Double pricePerHour;
	
	private TaxService taxService;
	
	

	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public TaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		long t1=carRental.getStart().getTime(); // data inicial em milissegundos
		long t2=carRental.getFinish().getTime(); // data final em milissegundos
		double basicPayment;
		
		double hours=(double)(t2-t1)/1000/60/60;
		double days=hours/24.0;
		
		if(hours<=12.0) {
			basicPayment=Math.ceil(hours)*pricePerHour;
		}
		
		else
			basicPayment=Math.ceil(days)*pricePerDay;
		
		double tax=taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
		
		

	}

}
