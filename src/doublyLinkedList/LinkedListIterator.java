package doublyLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedListIterator<T extends Comparable<T> & Serializable> implements Iterator<T> 
{
    private DoublyLinkNode<T> head;
    private DoublyLinkNode<T> current;
    private DoublyLinkNode<T> previous;
    private DoublyLinkNode<T> beforeprevious;
    private boolean removed;
    
    public LinkedListIterator(DoublyLinkNode<T> head){
    	this.head = head;
    	this.current = head.getNext();
    	this.previous = head;
    	this.beforeprevious = null;
    	this.removed = false;
    }
    
    public boolean hasNext(){
    	return (current.getData() != null);
    }
    
    public T next(){
    	if (hasNext()){
	    	T data = current.getData();
	    	beforeprevious = previous;
	    	previous = current;        //previous will reference the Node whose data we are returning
	    	current = current.getNext();//get ready to return the next value, next time this method is invoked
	    	removed = false;  //this data was not removed
	    	return data;
    	}
    	return null;
    }
    
    public void reset(){
    	this.current = head.getNext();
    }
    
    public void remove(){
    	if (removed){
    		return;
    	}
    	else{
    		//get ready to remove the Node that contains the data the user just viewed
    		//that node is pointed to by previous
    		removed =true;
    		previous = beforeprevious;  //because previous is being removed from the list
    		beforeprevious.setNext(current);
    		
    		removed = true;
    	}
    	
    }
}
