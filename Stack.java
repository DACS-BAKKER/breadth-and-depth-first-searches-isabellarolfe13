
public class Stack {
	private Node top;
	private Node next;
	
	public Stack() {
		top=null;
	}

	public Node Peek() {
		return top;
	}

	public void Push(int x, int y) {
		if(top==null) {
			top=new Node(x, y);
		}
		else {
			Node temp=top;
			top=new Node(x,y);
			top.SetNext(temp);
		}
	}


	public Node Pop(){
		Node temp1=top;
		if(top!=null) {
			top=top.getNext();
		}
		return temp1;
	}

	public void print() {
		System.out.println(top);
		Node temp=top;
		while(temp.getNext()!=null){
			temp=temp.getNext();
			temp.getLocation().print();
		}
	}

	

}





