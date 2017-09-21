/**
 * Represents the key in the FlightNode. Stores origin, destination, date and
 * time. Implements Comparable<FlightKey>.
 */
public class FlightKey implements Comparable<FlightKey> {
	// keys: origin, destination, date, time
	private String or;
	private String dest;
	private String date;
	private String time;

	FlightKey(String or, String dest, String date, String time) {
		this.or = or;
		this.dest = dest;
		this.date = date;
		this.time = time;
	}

	FlightKey(FlightKey other) {
		this.or = other.getOrigin();
		this.dest = other.getDest();
		this.date = other.getDate();
		this.time = other.getTime();
	}

	public String getOrigin() {
		return this.or;
	}

	public String getDest() {
		return this.dest;
	}

	public String getDate() {
		return this.date;
	}

	public String getTime() {
		return this.time;
	}

	// Orgin > dest > data > time
	public int compareTo(FlightKey o) {
		int orCompare = this.or.compareTo(o.getOrigin());
		if (orCompare != 0)
			return orCompare;
		else {
			int destCompare = this.dest.compareTo(o.getDest());
			if (destCompare != 0)
				return destCompare;
			else {
				int dateCompare = compareDate(this.date, o.getDate());
				if (dateCompare != 0)
					return dateCompare;
				else {
					return compareHour(this.time, o.getTime());
				}
			}
		}
	}

	private int compareHour(String time1, String time2) {
		String [] t1 = time1.split(":");
		String [] t2 = time2.split(":");
		
		return t1[0].compareTo(t2[0]);
		
	}

	private int compareDate(String date1, String date2) {
		String[] t1 = date1.split("/");
		String[] t2 = date2.split("/");
		
		int day = t1[0].compareTo(t2[0]);
		if (day != 0)
			return day;
		else {
			return t1[1].compareTo(t2[1]);
		}
	}

	public String toString() {
		return "(" + this.or + ", " + this.dest + ", " + this.date + ", " + this.time + ")";
	}
}
