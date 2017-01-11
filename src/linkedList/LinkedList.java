package linkedList;

import java.io.Serializable;
import java.util.Iterator;

import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T> & Serializable> implements Serializable, Iterable<T>
{
	private Node<T> head;
	
	/**
	 * constructor
	 */
	public LinkedList()
	{
		head = null;
	}
	
	/**
	 * add a link to the linked list
	 * @param data the data to add to the linked list
	 */
	public void add(T data)
	{
		//set up a new node to reference the data which will be added to the linked list
		Node<T> newNode = new Node<T>(data);
		//now you add the node to the linked list
		//if the linked list is empty then add the node to the head
		if(head == null)
		{
			head = newNode;
		}
		//if the linked list already has nodes, then add the new node to the end of the linked list
		//in order to add to the end you have to iterate through the list to find the end of the linked list
		else
		{
			Node<T> currentNode = head;
			Node<T> previousNode = head;
			while(currentNode != null)
			{
				previousNode = currentNode;
				currentNode = currentNode.getNext();
			}
			//you will get out of the loop when you found the end of the linked list
			//so now you can add the new node to the end
			previousNode.setNext(newNode);
		}
	}
	
	/**
	 * add a link to the beginning of the list
	 * @param data the data to add to the beginning of the linked list
	 */
	public void addHead(T data)
	{
		//set up a new node to reference the data which will be added to the linked list
		Node<T> newNode = new Node<T>(data);
		//now you add the node to the linked list
		//if the linked list is empty then set the head to the new node
		if(head == null)
		{
			head = newNode;
		}
		else
		{
			newNode.setNext(head);
			head = newNode;
		}
	}
	
	/**
	 * remove a node from the linked list
	 * @param data the data to be removed
	 * @return true or false if removed
	 */
	public boolean remove(T data)
	{
		if(head == null)
		{
			return false;	//the linked list is empty and there is nothing to remove
		}
		Node<T> currentNode = head;
		Node<T> previousNode = head;
		while(currentNode != null)
		{
			if(currentNode.getData().compareTo(data) == 0)
			{
				//you found the data you want to remove so you have to rearrange the links of the linked list
				if(currentNode == head)
				{
					//reset the head
					head = currentNode.getNext();
				}
				else
				{
					//the node you want to remove is in the middle
					previousNode.setNext(currentNode.getNext());
				}
				return true; //the node was removed
			}
			//the node wasn't found so move onto the next node of the list
			previousNode = currentNode;
			currentNode = currentNode.getNext();
		}
		return false;		//the data wasn't found
	}
	
	/**
	 * remove the head from the linked list
	 * @return if removed
	 */
	public boolean removeHead()
	{
		if(head == null)
			return false;
		else
		{
			head = head.getNext();			//set the second node to the head
			return true;
		}
	}
	
	/**
	 * getter
	 * @return head
	 */
	public T getHead()
	{
		return head.getData();
	}
	
	/**
	 * tests if the linked list is empty
	 * @return
	 */
	public boolean isEmpty()
	{
		if(head == null)
			return true;
		else
			return false;
	}
	
	/**
	 * the code needs access to the LinkedListIterator which is an inner class 
	 * @return a reference to the LinkedListIterator
	 */
	public Iterator<T> iterator()
	{
		return new LinkedListIterator();
	}
	
	/**
	 * an iterator for the linked list
	 */
	class LinkedListIterator implements Iterator<T>
	{
		private Node<T> current;
		private boolean removeCalled;
		private Node<T> previous;
		private Node<T> beforePrevious;
		
		/**
		 * constructor
		 */
		public LinkedListIterator()
		{
			this.removeCalled = false;
			this.current = head;
			this.previous = null;
			this.beforePrevious = null;
		}
		
		/**
		 * the iterator instance will check to see what current is referencing
		 * @return if has next or not
		 */
		public boolean hasNext()
		{
			if(current != null)
				return true;
			else
				return false;
		}
		
		/**
		 * invokes the hasNext that verifies that there is more data left that hasn't been returned
		 * saves a reference to the data referenced by the current node
		 * adjust the reference, precious and beforePrevious
		 * modifies the current so that is references the following node
		 * @return data
		 */
		public T next()
		{
			if(hasNext())
			{
				T currentData = current.getData(); //the data that will be returned
				
				// must continue to keep track of the Node that is in front of
				// the current Node whose data is must being returned , in case
				// its nextNode must be reset to skip the Node whose data is
				// just being returned
				beforePrevious = previous;
				
				// must continue keep track of the Node that is referencing the
				// data that was just returned in case the user wishes to remove()
				// the data that was just returned
				
				previous = current; // get ready to point to the Node with the next data value
				
				current = current.getNext(); // move to next Node in the chain, get ready to point to the next data item in the list
				
				this.removeCalled = false;
				// it's now pointing to next value in the list which is not the one that may have been removed
				
				return currentData;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}
		
		/**
		 * remove a node form the linked list
		 * the iterator can't process the remove if:
		 * 	the iterator is pointing to the end of the list (current == null)
		 * 	the node the iterator is pointing to was just removed (removeCalled == true)
		 * 
		 */
		public void remove()
		{
			if(this.removeCalled || previous == null)
			{
				throw new IllegalStateException();
			}
			
			//the data that must be removed is referenced by the node that is before the current since
			//the Next() method removes the pointer over after it returns what was pointed to by current
			else
			{
				//remove the data that is referenced by the first node
				if(previous == head)
				{
					head = previous.getNext();
					previous = null; //there is no node in front of the new head of the list
				}
				else
				{
					previous = beforePrevious;
					beforePrevious.setNext(current);
				}
				this.removeCalled = true;
			}
		}
	}
}

