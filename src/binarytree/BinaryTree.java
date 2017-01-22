package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>>
{
	private BTNode<T> root;
	private boolean found;
	
	public BinaryTree()
	{
		root = null;
	}
	
	public boolean insert(T data)
	{
		BTNode<T> current = root;
		BTNode<T> parent = root;
		BTNode<T> newNode;
		
		//iterate to insert data
		if(root == null)
			root = new BTNode<T>(data);
		else
		{
			while(current != null)
			{
				if(data.compareTo(current.getData())<0)
				{
					//data is less that the parent and should go down the left branch
					parent = current;
					current = current.getLeftChild();
				}
				else if(data.compareTo(current.getData())>0)
				{
					//data is greater than parent and should go down right branch
					parent = current;
					current = current.getRightChild();
				}
				else
					return false;	//duplicate value
			}
			//found the right place
			newNode = new BTNode<T>(data);
			if(newNode.compareTo(parent)<0)
				parent.setLeftChild(newNode);
			else
				parent.setRightChild(newNode);
		}
		return true;
	}
	
	public ArrayList<T> traverseInOrder()
	{
		//temporary array list that will be returned with all the values of the tree
		ArrayList<T> temp = new ArrayList<T>();
		
		//keep getting the left child of the left child until there are no more and then take the node 
		//and then the right child and then keep going up...
		traverseInOrder(root.getLeftChild(), temp);
		temp.add(root.getData());
		traverseInOrder(root.getRightChild(), temp);
		return temp;
	}
	
	private void traverseInOrder(BTNode<T> root, ArrayList<T> temp)
	{
		if(root == null) return;
		traverseInOrder(root.getLeftChild(), temp);
		temp.add(root.getData());
		traverseInOrder(root.getRightChild(), temp);
		
	}
	
	public ArrayList<T> traverseLevelOrder()
	{
		//temporary array list that will be returned with all the values of the tree
		ArrayList<T> temp = new ArrayList<T>();
		//use a queue to go through all the nodes level order
		Queue<BTNode<T>> queue = new LinkedList<BTNode<T>>();  
		queue.add(root);  
		while(!queue.isEmpty())  
		{  
			BTNode<T> tempNode = queue.poll();  
			temp.add(tempNode.getData());
			if(tempNode.getLeftChild() != null)  
				queue.add(tempNode.getLeftChild());  
			if(tempNode.getRightChild() != null)  
				queue.add(tempNode.getRightChild());  
		}  
		
		return temp;
	}
	
	public ArrayList<T> traversePreOrder()
	{
		ArrayList<T> temp = new ArrayList<T>();
		temp.add(root.getData());
		traversePreOrder(root.getLeftChild(), temp);  
		traversePreOrder(root.getRightChild(), temp);  
		return temp;
	}
	
	private void traversePreOrder(BTNode<T> root, ArrayList<T> temp)
	{
		if(root == null) return;
		temp.add(root.getData());
		traversePreOrder(root.getLeftChild(), temp);  
		traversePreOrder(root.getRightChild(), temp);
	}
	
	public ArrayList<T> traversePostOrder()
	{
		ArrayList<T> temp = new ArrayList<T>();
		traversePostOrder(root.getLeftChild(), temp);  
		traversePostOrder(root.getRightChild(), temp);      
		temp.add(root.getData());   
		return temp;
	}
	
	private void traversePostOrder(BTNode<T> root, ArrayList<T> temp)
	{
		if(root == null) return;
		traversePostOrder(root.getLeftChild(), temp);  
		traversePostOrder(root.getRightChild(), temp);      
		temp.add(root.getData());
	}

	public boolean removeVal(T value)
	{
		//to remove a value must start searching for it at the root
   	 	root = removeNode(value, root);
   	 	return found;  //return value in instance variable set by private method
    }
    
    private BTNode<T> removeNode(T value, BTNode<T> tree)
    {
   	 	//looks for value in the subtree
   	  	if (tree == null)
   	  		found = false;
   	  	else if (value.compareTo(tree.getData())< 0)
   	  		//recursive call further down the left side of tree
   	  		//might have to reset links if a node further down
   	  		//is set to null
   	  		tree.setLeftChild(removeNode(value,tree.getLeftChild()));
   	  	else if (value.compareTo(tree.getData())> 0)
   	  		//recursive call further down the right side of the tree
   	  		//might have to reset links if a node further down
   	  		//is set to null
   	  		tree.setRightChild(removeNode(value,tree.getRightChild()));
   	  	else
   	  	{   
   	  		//found the value , now remove that data from the tree
   	  		tree = removeData(tree);
   	  		found = true;
   		}
   	  	return tree;
   	 }
   	 
    private BTNode<T> removeData(BTNode<T> tree)
    {
		//case 1 and 2: subtree just has one child branch so return that 
	    // branch and link that branch to previous
	    // part of tree, basically eliminating the BNode
	    // in which the data was found		
   	 	if (tree.getLeftChild() == null)
   	 		return tree.getRightChild();
   	 	else if (tree.getRightChild() == null)
   	 		return tree.getLeftChild();
   	 	else
   	 	{  
   	 		//data is in a BNode that has two children.
   	 		//It is too complicated to remove this type of Node
   	 		//Instead do the following:
   	 		//a. Replace the data in that BNode with data that
   	 		//logically precedes that data - this data will be found in
   	 		//a leaf BNode 
   	 		//b. eliminate the leaf BNode by re-invoking the 
   	 		//  removeNode() method
   	 		T data = findPredecessor(tree.getLeftChild());
   	 		tree.setData(data);
   	 		tree.setLeftChild(removeNode(data,tree.getLeftChild()));
   	 		return tree;
   	 	}
    }
	
    private T findPredecessor(BTNode<T> tree)
    {
		//the Node that contains data that precedes a Node
		//can be found by going down till one hits the right most leaf
		//of its left branch
		while (tree.getRightChild() != null)
		{
			tree = tree.getRightChild();
		}
		return tree.getData();
	}
    
    public void optimize()
    {
    	ArrayList<T> temp = new ArrayList<T>();
    	//put the data into the arraylist in order so you can optimize
    	temp = traverseInOrder();
    	//clear the data pointing to the original tree so you can insert 
    	//the data once it's optimized
    	root = null;
    	
    	//find the middle value of the arraylist to put as the root of the tree
    	optimize2(temp, 0, temp.size()-1);
    	
    	//continue going recursively until you finished going through the whole 
    	//arraylist and there are no elements left

    }

	private void optimize2(ArrayList<T> temp, int start, int end) 
	{
		int place = (start + end)/2;
		while(start < end && !temp.isEmpty())
		{
			place = (start + end)/2;	//find the middle element
	    	//add that middle value to the binary tree and remove it from the arraylist
	    	insert(temp.remove(place));
	    	//keep calling on a smaller part of the arraylist to get a tree as balanced as possible
	    	optimize2(temp, start, place);
	    	optimize2(temp, place, temp.size());
		}
	}
    
}
