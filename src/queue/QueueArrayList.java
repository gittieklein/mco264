package queue;

import java.util.ArrayList;

public class QueueArrayList<T>
{
	private ArrayList<T> queue;
	
	public QueueArrayList()
	{
		queue = new ArrayList<T>();
	}
	
	public void enqueue(T item)
	{
		queue.add(item);
	}
	
	public T dequeue()
	{
		if(!isEmpty())
			return queue.remove(0);
		else
			throw new EmptyQueueException();
	}
	
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}
	
	
	public static void main(String[] args)
	{
		QueueArrayList<String> list = new QueueArrayList<String>();
		list.enqueue("Tzipporah");
		list.enqueue("Devorah");
		list.enqueue("Malka B");
		list.enqueue("Gittie");
		
		while(!list.isEmpty())
		{
			System.out.println(list.dequeue());
		}
	}
}
