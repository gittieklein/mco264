package queue;

import java.util.LinkedList;

public class QueueLinkedList<T>
{
	private LinkedList<T> queue;
	
	public QueueLinkedList()
	{
		queue = new LinkedList<T>();
	}
	
	public void enqueue(T item)
	{
		queue.addLast(item);
	}
	
	public T dequeue()
	{
		return queue.removeFirst();
	}
	
	public boolean isEmpty()
	{
		return (queue.getFirst() == null);
	}
}
