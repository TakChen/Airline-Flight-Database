/**
 * Represents data in the FlightNode. Contains the flight number and the price
 */
public class FlightData {
	// FILL IN CODE: add private variables: flightNumber and price
	private String flightNumber;
	private double price;
	
	FlightData(String fnum, double price) {
		// FILL IN CODE
		this.flightNumber = fnum;
		this.price = price;
	}

	public String getFlightNumber() {
		// FILL IN CODE
		return flightNumber;
	}

	public double getPrice() {
		// FILL IN CODE
		return price;
	}
}
