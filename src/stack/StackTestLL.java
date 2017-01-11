package stack;

public class StackTestLL 
{
	public static void main(String[] args)
	{
		StackLinkedList<String> stackList = new StackLinkedList<String>();
		stackList.push("word 1");
		stackList.push("word 2");
		stackList.push("word 3");
		stackList.push("word 4");
		stackList.push("word 5");
		
		while(!stackList.isEmpty())
		{
			System.out.println(stackList.peek());
			stackList.pop();
		}
	}
}
