/**
 * Represents data in the FlightNode. Contains the flight number and the price
 */
public class FlightData {
	private String flightNumber;
	private double price;
	
	FlightData(String fnum, double price) {
		this.flightNumber = fnum;
		this.price = price;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public double getPrice() {
		return price;
	}
}
