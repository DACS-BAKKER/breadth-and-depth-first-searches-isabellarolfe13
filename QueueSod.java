
public class QueueSod {
	private NodeSod head;
	private NodeSod tail;
	public QueueSod() {
		head=null;
		tail=null;
	}
	//adds
	public void enqueue(NodeSod n) {
		if(head==null) {
			head=n;
		}
		if(tail==null) {
			tail=n;
		}
		else {
			tail.SetNext(n);
			tail=n;
		}
	}
	//removes
	public NodeSod dequeue() {
		NodeSod headd=new NodeSod(head.getBoard());
		if(head!=null) {
			head=head.getNext();
		}
		return headd;
	}
	public int size() {
		if(head==null)
        {
            return 0;
        }
		int x=1;
		NodeSod temp=head;
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			x++;
		}
		return x;
	}
	public NodeSod Peek() {
		return head;
	}

}
