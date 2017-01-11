package linkedList;

import java.io.Serializable;

public class Node<T extends Comparable<T> & Serializable> implements Comparable<Node<T>>, Serializable
{
	private T data; 				//the data that the node is referencing
	private Node<T> nextNode; 	//this is the Node that this Node is pointing to, next on the chain
	
	/**
	 * constructor
	 * @param data the data that will be the node added to the linked list
	 */
	public Node(T data)
	{
		this.data = data;
		this.nextNode = null;	//the last node on the chain
	}
	
	/**
	 * modify the data that the node is referencing
	 * connect a node to another node
	 * @param next what to set the next node to
	 */
	public void setNext(Node<T> next)
	{
		this.nextNode = next;
	}
	
	/**
	 * Retrieve the next node
	 * @return the next node the linked list is pointing to
	 */
	public Node<T> getNext()
	{
		return nextNode;
	}
	
	/**
	 * retrieve data the the Node is referencing
	 * @return the data of the current node
	 */
	public T getData()
	{
		return this.data;
	}
	
	/**
	 * compareTo compares the 2 nodes based on the data
	 * @return the int value based on the comparison of the 2 nodes
	 */
	public int compareTo(Node<T> node)
	{
		return this.data.compareTo(node.data);
	}
}
