
public class StackSod {

	private NodeSod top;
	private NodeSod next;
	
	public StackSod() {
		top=null;
	}

	public NodeSod Peek() {
		return top;
	}

	public void Push(NodeSod n) {
		if(top==null) {
			top=n;
		}
		else {
			NodeSod temp=top;
			top=new NodeSod(n.getBoard());
			top.SetNext(temp);
		}
	}

	public NodeSod Pop(){
		NodeSod temp1=top;
		if(top!=null) {
			top=top.getNext();
		}
		return temp1;
	}
	//delete before!!
	public int size(){
        if(top==null)
        {
            return 0;
        }
        int counter = 1;
        NodeSod current = top;
        while(current.getNext()!=null)
        {
            current = current.getNext();
            counter++;
        }
        return counter;
    }

//	public void print() {
//		System.out.println(top);
//		Node temp=top;
//		while(temp.getNext()!=null){
//			temp=temp.getNext();
//			temp.getLocation().print();
//		}
//	}
}
