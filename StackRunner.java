
public class StackRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node newnode=new Node(0,0);
		Stack stack=new Stack();
		stack.Push(4, 5);
		stack.Push(2, 1);
		stack.Push(3, 9);
		stack.Push(1, 2);
		System.out.println(stack.Peek());
		stack.Push(5, 9);
		System.out.println(stack.Peek());
		stack.Pop();
		System.out.println(stack.Peek());

		

	}

}
