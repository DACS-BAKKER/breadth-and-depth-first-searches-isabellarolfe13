
public class Node {
	private Location loc;
	private Node next;
	private int prevCheck;

	public Node(int x, int y) {
		loc=new Location(x,y);
	}
	public Node getNext() {
		return next;
	}
	public void SetNext(Node n) {
		next=n;
	}
	public void print() {
		System.out.println("(" + loc.getX()+" , " + loc.getY() + ")");
	}
	public Location getLocation() {
		return loc;
	}


}
