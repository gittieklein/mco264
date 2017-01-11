package doublyLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class ReverseIterator<T extends Comparable<T> & Serializable> implements Iterator<T> 
{
	private DoublyLinkNode<T> current;
	private boolean removeCalled;
	private DoublyLinkNode<T> previous;
	private DoublyLinkNode<T> beforeprevious;
	
	public ReverseIterator(DoublyLinkNode<T> tail)
	{
		this.removeCalled = false;
		this.current = tail.getPrev();
		this.previous = tail;
		this.beforeprevious = null;
	}
	
	public boolean hasNext() 
	{
		return (current.getData() != null);
	}

	public T next() 
	{
		if(hasNext())
		{
			T currentData = current.getData();
			beforeprevious = previous;
			previous = current;
			current = current.getPrev();
			this.removeCalled = false;
			return currentData;
		}
		else
		{
			throw new NotFoundException();
		}
	}
	
	public void remove()
	{
		if(this.removeCalled || previous == null)
		{
			throw new IllegalStateException();
		}
		else
		{
			previous = beforeprevious;
			beforeprevious.setPrev(current);
			current.setNext(beforeprevious);
		}
		this.removeCalled = true;
	}

}
