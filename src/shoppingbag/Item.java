package shoppingbag;

import java.io.Serializable;

import Exceptions.InvalidDataException;

public class Item implements Serializable, Comparable<Item>
{
	private String description;
	private double price;
	
	public Item(String description, double price)
	{
		if(description != null && price > 0)
		{
			this.description = description;
			this.price = price;
		}
		else
		{
			throw new InvalidDataException();
		}
	}
	
	//copy constructor
	public Item(Item item)
	{
		description = item.getDescription();
		price = item.getPrice();
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		if(price > 0)
		{
			this.price = price;
		}
		else
		{
			throw new InvalidDataException();
		}
	}
	
	public int compareTo(Item i)
	{
		return description.compareTo(i.description);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\nItem:");
		sb.append("\ndescription: " + description);
		sb.append("\nprice: " + price);
		
		return sb.toString();
	}
}
