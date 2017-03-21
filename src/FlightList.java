import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/** The class that represents the flight database using a skip list */
public class FlightList {

	// FILL IN CODE: needs to store the head, the tail and height of the skip
	// list
	private FlightNode head;
	private FlightNode tail;
	private int height;

	public FlightList() {
		height = 2;
		initializateLayer();
	}

	/** Reads flight data from the file and inserts it into this skip list */
	public FlightList(String filename) {
		initializateLayer();
		height = 2;
		String line = null;
		try (BufferedReader bf = new BufferedReader(new FileReader(filename));) {
			while ((line = bf.readLine()) != null) {
				String[] info = line.split(" ");
				FlightKey key = new FlightKey(info[0], info[1], info[2], info[3]);
				FlightData data = new FlightData(info[4], Double.parseDouble(info[5]));
				boolean inser = insert(key, data);
			}
		} catch (IOException e) {
			System.out.println("FlightList.constructor has IOException");
		}
	}

	/**
	 * Returns true if the node with the given key exists in the skip list,
	 * false otherwise. This method needs to be efficient.
	 * 
	 * @param key
	 * @return
	 */
	public boolean find(FlightKey key) {
		FlightNode temp = findNode(key);
		if (temp.getKey().compareTo(key) == 0)
			return true;
		else
			return false;
	}

	private FlightNode findNode(FlightKey key) {
		FlightNode temp = head;
		while (temp.getDown() != null) {
			temp = temp.getDown();
			while (temp.getNext().getKey().compareTo(key) <= 0) {
				temp = temp.getNext();
			}
		}
		return temp;
	}

	private void initializateLayer() {
		// Dummy Layer
		head = new FlightNode(new FlightKey(" ", null, null, null), null);
		tail = new FlightNode(new FlightKey("~~~~", null, null, null), null);
		head.setNext(tail);
		tail.setPrev(head);

		// Base layer
		FlightNode baseHead = new FlightNode(head);
		FlightNode baseTail = new FlightNode(tail);
		baseHead.setNext(baseTail);
		baseTail.setPrev(baseHead);

		head.setDown(baseHead);
		baseHead.setUp(head);
		tail.setDown(baseTail);
		baseTail.setUp(tail);
	}

	/**
	 * Insert a (key, value) pair to the skip list. Returns true if it was able
	 * to insert.
	 */
	public boolean insert(FlightKey key, FlightData data) {
		// check key if in the list
		if (find(key)) {
			System.out.println("found in the list");
			return false;
		}
		// toss coin + build tower
		Random rand = new Random();
		int nodeLevel = 1;
		FlightNode newNode = new FlightNode(key, data);
		FlightNode pointer = newNode;
		while (rand.nextBoolean()) {
			FlightNode temp = new FlightNode(newNode);
			newNode.setUp(temp);
			temp.setDown(newNode);
			newNode = temp;
			nodeLevel++;
		}
		// compare Height and level
		if (height > nodeLevel) { // 4 > 3
			insert(nodeLevel, newNode);
		}
		/*
		 * height=2, level=3 | height=2, level=2, height must be bigger or equal
		 * level + 1 add layer
		 */
		else {
			addlayer(nodeLevel + 1);
			insert(nodeLevel, newNode);
		}

		return true;
	}

	private void insert(int nodeLevel, FlightNode newNode) {
		int currentLevel = height;
		FlightNode curr = head;
		while (curr.getDown() != null) {
			curr = curr.getDown();
			currentLevel--;
			while (curr.getNext().getKey().compareTo(newNode.getKey()) <= 0) {
				curr = curr.getNext();
			}
			if (currentLevel <= nodeLevel) {
				newNode.setNext(curr.getNext());
				newNode.setPrev(curr);
				curr.setNext(newNode);
				newNode.getNext().setPrev(newNode);
				newNode = newNode.getDown();
			}
		}
	}

	/**
	 * add several layers to current skip list
	 * 
	 * @param count
	 *            - the expected height after adding
	 */
	private void addlayer(int expected) {
		while (height != expected) {
			FlightNode h = new FlightNode(head);
			FlightNode t = new FlightNode(tail);
			h.setNext(t);
			t.setPrev(h);

			head.setUp(h);
			h.setDown(head);
			tail.setUp(t);
			t.setDown(tail);
			head = h;
			tail = t;
			height++;
		}
	}

	/**
	 * Returns the list of nodes that are successors of a given key. Refer to
	 * the project pdf for a detailed description of the method.
	 * 
	 * @param key
	 * @return
	 */
	public ArrayList<FlightNode> successors(FlightKey key) {
		ArrayList<FlightNode> arr = new ArrayList<FlightNode>();

		FlightNode temp = findNode(key);

		while (temp.getDown() != null)
			temp = temp.getDown();

		temp = temp.getNext();

		while (temp.getKey().getOrigin().compareTo(key.getOrigin()) == 0
				&& temp.getKey().getDest().compareTo(key.getDest()) == 0
				&& temp.getKey().getDate().compareTo(key.getDate()) == 0
				&& temp.getKey().getTime().compareTo(key.getTime()) >= 0) {
			arr.add(temp);
			temp = temp.getNext();
		}

		return arr;
	}

	/**
	 * Returns the list of nodes that are predecessors of a given key. Refer to
	 * the project pdf for a detailed description of the method.
	 * 
	 * @param key
	 * @return
	 */
	public ArrayList<FlightNode> predecessors(FlightKey key) {
		ArrayList<FlightNode> arr = new ArrayList<FlightNode>();
		FlightNode temp = findNode(key);

		while (temp.getDown() != null)
			temp = temp.getDown();

		if (temp.getKey().compareTo(key) == 0)
			temp = temp.getPrev();

		while (temp.getKey().getOrigin().compareTo(key.getOrigin()) == 0
				&& temp.getKey().getDest().compareTo(key.getDest()) == 0
				&& temp.getKey().getDate().compareTo(key.getDate()) == 0
				&& temp.getKey().getTime().compareTo(key.getTime()) <= 0) {
			arr.add(0, temp);
			temp = temp.getPrev();
		}

		return arr;

	}

	/**
	 * Prints the SkipList (prints the elements on all levels starting at the
	 * top. Each level should be printed on a separate line.
	 */
	public void print() {
		FlightNode horizon = head;
		FlightNode vertical = head;
		while (vertical != null) {
			StringBuffer sb = new StringBuffer();
			while (horizon != null) {
				sb.append(horizon.getKey() + ", ");
				horizon = horizon.getNext();
			}
			System.out.println(sb.toString());
			vertical = vertical.getDown();
			horizon = vertical;
		}
	}

	/**
	 * Returns a list of nodes that have the same origin and destination cities
	 * and the same date as the key, with departure times within the given time
	 * frame of the departure time of the key.
	 */
	public ArrayList<FlightNode> findFlights(FlightKey key, int timeFrame) {
		ArrayList<FlightNode> resFlights = new ArrayList<FlightNode>();

		FlightNode temp = findNode(key);

		// goto bottom
		while (temp.getDown() != null)
			temp = temp.getDown();
		// calculate time frame[pre]
		String[] time = key.getTime().split(":");
		int hour = Integer.parseInt(time[0]);
		int earlyHour = hour - timeFrame;
		// String preHour;
		// if (earlyHour > 9)
		// preHour = String.valueOf(earlyHour)+ ":" + "00";
		// else
		// preHour = "0" + earlyHour + ":" + "60";

		FlightNode forward = temp;
		while (forward.getKey().getOrigin().compareTo(key.getOrigin()) == 0
				&& forward.getKey().getDest().compareTo(key.getDest()) == 0
				&& forward.getKey().getDate().compareTo(key.getDate()) == 0
				&& Integer.parseInt(forward.getKey().getTime().split(":")[0]) >= earlyHour) {
			resFlights.add(forward);
			if (forward.getPrev() != null)
				forward = forward.getPrev();
			else
				break;
		}

		// calculate time frame[after]
		int lateHour = hour + timeFrame;
		String afterHour;
		// if (lateHour > 9)
		// afterHour = String.valueOf(lateHour) + ":" + "00";
		// else
		// afterHour = "0" + lateHour + ":" + "60";

		temp = temp.getNext();
		while (temp.getKey().getOrigin().compareTo(key.getOrigin()) == 0
				&& temp.getKey().getDest().compareTo(key.getDest()) == 0
				&& temp.getKey().getDate().compareTo(key.getDate()) == 0
				&& Integer.parseInt(temp.getKey().getTime().split(":")[0]) <= lateHour) {
			resFlights.add(temp);
			temp = temp.getNext();
		}

		return resFlights;
	}

}
