package doublyLinkedList;

import java.io.Serializable;

public class DoublyLinkNode<T extends Serializable & Comparable<T>> implements Serializable 
{
	protected T data;
	protected DoublyLinkNode<T> prev;
	protected DoublyLinkNode<T> next;

	public DoublyLinkNode() 
	{
		data = null;
		prev = null;
		next = null;
	}

	public DoublyLinkNode(T value) 
	{
		data = value;
		prev = null;
		next = null;
	}

	public void setPrev(DoublyLinkNode<T> aNode) 
	{
		prev = aNode;
	}

	public DoublyLinkNode<T> getPrev() 
	{
		return prev;
	}

	public void setNext(DoublyLinkNode<T> aNode) 
	{
		next = aNode;
	}

	public DoublyLinkNode<T> getNext() 
	{
		return next;
	}

	public T getData() 
	{
		return data;
	}
}