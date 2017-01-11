package shoppingbag;

import java.io.Serializable;
import java.util.Arrays;

import Exceptions.InvalidDataException;
import Exceptions.ItemNotFoundException;

public class ShoppingBag implements Serializable
{
	private int numItems;
	private double costItems;
	private Item[] items;
	
	public ShoppingBag(int num)
	{
		if(num > 0)
		{
			items = new Item[num];
		}
		else
		{
			throw new InvalidDataException();
		}
		costItems = 0;
		numItems = 0;
	}
	
	//add item to the shopping bag and update the total num of items and cost
	public void place(Item a)
	{
		if(numItems >= items.length)
		{
			addToBag();
		}
		items[numItems] = a;
		numItems++;
		costItems += a.getPrice();
	}
	
	//remove item from shopping bag based on description
	public Item removeItem(String description)
	{
		//boolean remove = false;
		//Item holder = null;
		Item holder = null;
		for(int i = 0; i < numItems; i++)
		{
			if(items[i].getDescription().equalsIgnoreCase(description))
			{
				holder = items[i];
				costItems -= items[i].getPrice();
				for(int j = i; j < numItems - 1; j++)
				{
					items[j] = items[j + 1];
				}
				items[numItems - 1] = null;
				numItems--;
				return holder;
			}
		}
		
		//if the item wasn't removed then it means it wasn't found
		throw new ItemNotFoundException();
		//there is no need for a return because it will never get to this point
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
		str.append("\nTotal items in shopping bag: " + numItems);
		str.append("\nTotal cost of shopping bag: " + String.format("$%.2f", costItems));
		str.append("\nItems:");
		for(int i = 0; i < numItems; i++)
		{
			str.append("\nItem " + (i+1) + ":\nDescription: " + items[i].getDescription());
			str.append("\nPrice: " + String.format("$%.2f", items[i].getPrice()));
		}
		str.append("\n");
		return str.toString();
	}
	
	private Item[] addToBag()
	{
		items = Arrays.copyOf(items, (items.length + 10));
		return items;
	}
}
