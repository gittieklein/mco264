package binarytree;

import java.util.ArrayList;

public class UseBinaryTree 
{
	public static void main(String[] args)
	{
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		
		bt.insert(1);
		bt.insert(2);
		bt.insert(3);
		bt.insert(4);
		bt.insert(5);	
		bt.insert(6);
		bt.insert(7);
		bt.insert(8);
		bt.insert(9);
		bt.insert(10);

		
		ArrayList<Integer> bt2 = new ArrayList<Integer>();
		
		System.out.println("Before Optimization");
		
		bt2 = bt.traverseLevelOrder();
		System.out.println("level order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		bt2.clear();
		
		bt2 = bt.traverseInOrder();
		System.out.println("in order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		bt2.clear();
		
		bt2 = bt.traversePreOrder();
		System.out.println("pre order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		bt2.clear();
		
		bt2 = bt.traversePostOrder();
		System.out.println("post order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		
		bt.optimize();
		
		bt2.clear();
		
		System.out.println("\n\n\nAfter Optimization");
		bt2 = bt.traverseLevelOrder();
		System.out.println("level order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		bt2.clear();
		
		bt2 = bt.traverseInOrder();
		System.out.println("in order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		bt2.clear();
		
		bt2 = bt.traversePreOrder();
		System.out.println("pre order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		bt2.clear();
		
		bt2 = bt.traversePostOrder();
		System.out.println("post order:");
		for(Integer temp: bt2) 
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
	}
	
	
}
