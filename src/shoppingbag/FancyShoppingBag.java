package shoppingbag;

import java.io.Serializable;
import java.util.Iterator;

import Exceptions.*;
import linkedList.*;


public class FancyShoppingBag implements Serializable
{
	private int numItems;
	private double costItems;
	private LinkedList<Item> items;
	
	public FancyShoppingBag()
	{
		items = new LinkedList<Item>();
		costItems = 0;
		numItems = 0;
	}
	
	//add item to the shopping bag and update the total num of items and cost
	public void place(Item a)
	{
		items.add(a);
		numItems++;
		costItems += a.getPrice();
	}
	
	//remove item from shopping bag based on description
	public Item removeItem(String description)
	{
		Iterator<Item> iter = items.iterator();
		Item i = null;
		while (iter.hasNext()){
			i = (Item) iter.next();
			if(i.getDescription().equals(description))
			{
				iter.remove();	//if you call the iter.remove() it will remove the node it just passe
				numItems--;
				costItems -= i.getPrice();
				return i;
			}
			
		}
		
		//if the item wasn't removed then it means it wasn't found
		throw new ItemNotFoundException();
	}
	
	public int getNumberOfItems()
	{
		return numItems;
	}	
	public double getValue()
	{
		return costItems;
	}
	
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("\nTotal items in fancy shopping bag: " + numItems);
		str.append("\nTotal cost of fancy shopping bag: " + String.format("$%.2f", costItems));
		str.append("\nItems:");
		int i = 1;
		for(Item var: items)
		{
			str.append("\nItem " + (i) + ":\nDescription: " + var.getDescription());
			str.append("\nPrice: " + String.format("$%.2f", var.getPrice()));
			i++;
		}
		str.append("\n");
		return str.toString();
	}
}
