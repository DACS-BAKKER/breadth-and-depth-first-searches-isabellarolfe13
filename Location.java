import org.omg.Messaging.SyncScopeHelper;

public class Location {
	public int x;
	public int y;

	public Location(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int num) {
		x=num;
	}
	public void setY(int num) {
		y=num;
	}
	public void print() {
		System.out.println(x + " " + y);
	}
	public String toString() {
		String s=x+" " +y;
		return s;
	}
}
