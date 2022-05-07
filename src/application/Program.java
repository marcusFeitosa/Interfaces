package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter rental data");
		System.out.print("Car model:"); 
		String model=sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy HH:mm):"); 
		String pickup=sc.nextLine();
		System.out.print("Return (dd/MM/yyyy HH:mm):"); 
		String end=sc.nextLine();
		
		Date start, finish;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		start=sdf.parse(pickup);
		finish=sdf.parse(end);
		
		CarRental carRental=new CarRental(start,finish,new Vehicle(model));
		
		System.out.print("Enter price per hour");
		double pricePerHour=sc.nextDouble();
		System.out.print("Enter price per day:");
		double pricePerDay=sc.nextDouble();
		
		RentalService rs=new RentalService(pricePerDay, pricePerHour, new BrazilTaxServices());
		
		rs.processInvoice(carRental);
		
		System.out.println("INVOICE:");
		System.out.println("Basic payment:"+String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax:"+String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total payment:"+String.format("%.2f", carRental.getInvoice().getTotalPayment()));
		
				
		sc.close();

	}

}
