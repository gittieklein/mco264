package stack;

import java.io.Serializable;
import java.util.Iterator;

import linkedList.*;


public class StackLinkedList<E extends Comparable<E> & Serializable> 
{
	private LinkedList<E> values;
	
	/**
	 * constructor
	 */
	public StackLinkedList()
	{
		values = new LinkedList<E>();
	}
	
	/**
	 * push a value onto the stack
	 * @param value
	 */
	public void push(E value)
	{
		values.addHead(value);
	}
	
	/**
	 * pop the first value of the stack and discard it
	 */
	public void pop()
	{
		boolean remove = values.removeHead();
		if(!remove)
			throw new EmptyStackException();
	}
	
	/**
	 * take a peek at the top of the stack
	 * @return the value at the top of the stack
	 */
	public E peek()
	{
		return values.getHead();
	}
	
	/**
	 * checks if stack is empty
	 * @return true or false
	 */
	public boolean isEmpty()
	{
		if(values.isEmpty())
			return true;
		else
			return false;
	}
	
	
}
