package stack;

import java.util.ArrayList;

public class StackArrayList<E>
{
	private ArrayList<E> values;

	/**
	 * constructor
	 */
	public StackArrayList()
	{
		values = new ArrayList<E>();
	}

	/**
	 * add element to the top of array list
	 * @param value
	 */
	public void push(E value)
	{
		values.add(value);
	}
	
	/**
	 * pop first element from stack
	 */
	public void pop()
	{
		if(!isEmpty())
			values.remove(values.size()-1);
		else
			throw new EmptyStackException();
	}
	
	/**
	 * peek at the first element of the array list stack
	 * @return the element that peeked at
	 */
	public E peek()
	{
		if(!isEmpty())
			return values.get(values.size()-1);
		else
			throw new EmptyStackException();
	}
	
	/**
	 * checks if the stack is empty
	 * @return true or false
	 */
	public boolean isEmpty()
	{
		return values.isEmpty();
	}
}
