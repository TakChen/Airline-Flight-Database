/**
 * The class that represents a node in a flight skip list. 
 * Contains the key of type FlightKey and the data of type FlightData. 
 * Also stores the following pointers to FlightNode(s): next, down, prev and up.
 */
public class FlightNode {

	// FILL IN CODE, declare instance variables (make them private)
	private FlightKey key;
	private FlightData data;
	private FlightNode next;
	private FlightNode down;
	private FlightNode prev;
	private FlightNode up;

	FlightNode(FlightNode node) {
		this.key = node.getKey();
		this.data = node.getData();


	}

	FlightNode(FlightKey key, FlightData data) {
		this.key = key;
		this.data = data;
	}

	// write getters and setters for all the private variables:
	public FlightKey getKey(){
		return this.key;
	}
	public FlightData getData(){
		return this.data;
	}
	public FlightNode getNext(){
		return this.next;
	}
	public FlightNode getDown(){
		return this.down;
	}
	public FlightNode getPrev(){
		return this.prev;
	}
    public FlightNode getUp(){
		return this.up;
	}
	
	public void setKey(FlightKey key){
		this.key = key;
	}
	public void setData(FlightData data){
		this.data = data;
	}
	public void setNext(FlightNode next){
		this.next = next;
	}
	public void setDown(FlightNode down){
		this.down = down;
	}
	public void setPrev(FlightNode prev){
		this.prev = prev;
	}
	public void setUp(FlightNode up){
		this.up = up;
	}

}
