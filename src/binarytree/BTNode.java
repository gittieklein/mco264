package binarytree;

import java.io.Serializable;

public class BTNode<T extends Comparable<T>> implements Comparable<BTNode<T>>, Serializable
{
	private T data;
	private BTNode<T> leftChild;
	private BTNode<T> rightChild;
	
	public BTNode()
	{
		data = null;
		leftChild = rightChild = null;
	}
	
	public BTNode(T data)
	{
		this.data = data;
		leftChild = rightChild = null;
	}
	
	public BTNode<T> getLeftChild()
	{
		return leftChild;
	}
	
	public BTNode<T> getRightChild()
	{
		return rightChild;
	}
	
	public T getData()
	{
		return data;
	}
	
	public void setLeftChild(BTNode<T> node)
	{
		leftChild = node;
	}
	
	public void setRightChild(BTNode<T> node)
	{
		rightChild = node;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	public int compareTo(BTNode<T> other)
	{
		return data.compareTo(other.getData());
	}
}
