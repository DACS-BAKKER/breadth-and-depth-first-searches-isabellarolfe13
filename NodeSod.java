
public class NodeSod {
	private NodeSod next=null;
	private int board[][];

	public NodeSod(int[][]board) {
		this.board=board;
	}
	public int[][] getBoard(){
		return board;
	}
	public NodeSod getNext() {
		return next;
	}
	public void SetNext(NodeSod n) {
		next=n;
	}
}