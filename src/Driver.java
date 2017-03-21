import java.util.ArrayList;
import java.util.Random;

public class Driver {
	public static void main(String[] args) {

		FlightList list = new FlightList();

		// list.print();

//		list = new FlightList("test.txt");

//		 FlightKey a = new FlightKey("BOS", "SFO", "01/03/2014", "11:40");
//		 FlightKey b = new FlightKey("FRA", "BOS", "01/03/2014", "09:30");
//		 FlightKey c = new FlightKey("FRA", "JFK", "01/03/2014", "05:50");
//		 FlightKey d = new FlightKey("C", "B", "01/03/2014", "07:10");
		 
//		 list.insert(a, null);
//		 list.insert(b, null);
//		 list.insert(c, null);
//		 
//		 list.print();
//		 ArrayList<FlightNode> nodes = new ArrayList<>();
//		 nodes = list.findFlights(c, 2);
//		 
//		 for (int i = 0; i < nodes.size(); i++) {
//			System.out.println(nodes.get(i).getKey());
//		}
		
		int i = "06:00".compareTo("07:10");
		System.out.println(i);

	}

}
