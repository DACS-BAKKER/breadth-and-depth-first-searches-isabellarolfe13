
public class NodeBreath {
	private Location loc;
	private NodeBreath parent;
	private NodeBreath next;

	public NodeBreath(int x, int y, NodeBreath parent) {
		loc=new Location(x,y);
		setParent(parent);
	}
	public NodeBreath getParent() {
		return parent;
	}
	public void setParent(NodeBreath n) {
		parent=n;
	}
	public void print() {
		System.out.println("(" + loc.getX()+" , " + loc.getY() + ")");
	}
	public Location getLocation() {
		return loc;
	}
	public void setNext(NodeBreath n) {
		next=n;
	}
	public NodeBreath getNext() {
		return next;

	}
}
