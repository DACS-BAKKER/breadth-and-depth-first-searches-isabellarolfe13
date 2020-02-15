
public class Queue {
	private NodeBreath head;
	private NodeBreath tail;
	int n = 0;
	public Queue() {
		head=null;
		tail=null;
	}
	public void enqueue(NodeBreath n) {
		if(head==null) {
			head=n;
		}
		if(tail==null) {
			tail=n;
		}
		else {
			tail.setNext(n);
			tail=n;
		}
	}
	public NodeBreath Peek() {
		return head;
	}
	public void dequeue() {
		if(head!=null) {
			head=head.getNext();
		}
	}

}
