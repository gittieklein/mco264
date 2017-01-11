package doublyLinkedList;

import java.io.Serializable;

public class DoublyLinkedList<T extends Comparable<T> & Serializable> implements Serializable, Iterable<T>
{
	private DoublyLinkNode<T> head;
	
	public DoublyLinkedList()
	{
		head = new DoublyLinkNode<T>(null);
		head.setNext(head);
		head.setPrev(head);
	}
	
	public void insert(T data)
	{
		DoublyLinkNode<T> newNode = new DoublyLinkNode<T>(data);
		DoublyLinkNode<T> current = head.getNext();
		DoublyLinkNode<T> previous = head;
		
		while (current != head)
		{
			if(data.compareTo(current.getData()) < 0)
			{
				break;	//you found the place that you want to add it
			}
			else
			{
				//move along to the next node
				previous = current;
				current = current.getNext();
			}
		}
		
		newNode.setNext(current);
		previous.setNext(newNode);
		newNode.setPrev(previous);
		current.setPrev(newNode);
		return;
	}
		
	public void remove(T data) throws NotFoundException
	{
		DoublyLinkNode<T> current = head.getNext();
		DoublyLinkNode<T> previous = head;
		while(current != head)
		{
			if(current.getData().compareTo(data) == 0)
			{
				previous.setNext(current.getNext());
				current.getNext().setPrev(previous);
				return;
			}
			
			previous = current;
			current = current.getNext();
		}
		throw new NotFoundException();
	}
	
	public ReverseIterator<T> reverseIterator()
	{
		return new ReverseIterator<T>(head);
	}
	
	public LinkedListIterator<T> iterator()
	{
		return new LinkedListIterator<T>(head);
	}
}

