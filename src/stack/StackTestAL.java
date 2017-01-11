package stack;

public class StackTestAL 
{
	public static void main(String[] args)
	{
		StackArrayList<String> stackList = new StackArrayList<String>();
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
