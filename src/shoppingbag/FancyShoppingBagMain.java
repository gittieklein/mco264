package shoppingbag;

import java.util.Scanner;

import Exceptions.InvalidDataException;
import Exceptions.ItemNotFoundException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FancyShoppingBagMain 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		FancyShoppingBag myFancyShoppingBag = null;
		int choice;
		
		System.out.println("Would you like to: "
						+ "\n1. start a new application"
						+ "\n2. restore data from disk");
		int slct = input.nextInt();
		input.nextLine();
		
		if(slct == 2)
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fancyshoppingbag.ser"));
				myFancyShoppingBag = (FancyShoppingBag) ois.readObject();
				System.out.println("Your shopping bag was successfully restored.");
			}
			catch(IOException e)
			{
				System.out.println(e);
				System.out.println("The file can't be found. \nPlease contact IT.");
				System.exit(1);
			}
			catch(ClassNotFoundException x)
			{
				System.out.println(x);
				System.out.println("The classes don't match. \nPlease contact IT.");
				System.exit(1);
			}
		}
		else
		{
			//there is no need to ask the user how many items
			myFancyShoppingBag = new FancyShoppingBag();
		}
		
		String description;
		double price;
		
		do
		{		
			choice = menu();
			switch(choice)
			{
				case 1:
					System.out.print("A short description of the item: ");
					description = input.nextLine();
					System.out.print("Price of item: ");
					price = input.nextDouble();
					input.nextLine();
					try
					{
						Item item = new Item(description, price);
						myFancyShoppingBag.place(item);
						System.out.println("Your item was successfuly added to the shopping bag.\n");
					}
					catch(InvalidDataException e)
					{
						System.out.println(e);
						System.out.println("The price must be positive and the description much "
								+ "contain at least one character.");
						System.out.println("Your item was not added to the bag.\n");
					}
					break;
				case 2:
					System.out.print("Please enter the description of the item you would like to remove: ");
					description = input.nextLine();
					try
					{	
						myFancyShoppingBag.removeItem(description);
						System.out.println("Your item was successfully removed from the shopping bag.\n");
					}
					catch(ItemNotFoundException e)
					{
						System.out.println(e);
						System.out.println("No item was found with the description \"" + description + "\".");
						System.out.println("Your item was not removed from the bag.\n");
					}
					break;
				case 3:
					System.out.println(myFancyShoppingBag.toString());
					break;
				case 4:
					System.out.println("Exiting application.");
					try
					{
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fancyshoppingbag.ser"));
						oos.writeObject(myFancyShoppingBag);
						System.out.println("All data is saved to fancyshoppingbag.ser.");
						System.exit(0);
					}
					catch(IOException e)
					{
						System.out.println(e);
						System.out.println("Data couldn't be stored. \nContact IT.");
					}
					break;
				case 5:
					System.out.println("Good bye");
					System.exit(0);
					break;
				//I did not put a default because there is input validation
				//so the choice will have to equal 1-5			
			}//end switch
			//you want to keep looping unless the user decides to exit
			//i didn't do if he chooses choice 4 because if it fails to save then
			//it will continue to loop
		}while(choice != 5);
	}//end main
	
	public static int menu()
	{
		Scanner kybd = new Scanner(System.in);
		int choice;
		do{
			System.out.println("Please select from the following menu: ");
			System.out.println("1. Add Item"
						   + "\n2. Remove Item"
						   + "\n3. View Shopping Bag"
						   + "\n4. Save Contents to File"
						   + "\n5. Close Application Without Saving");
			choice = kybd.nextInt();
			kybd.nextLine();
		}while(choice < 1 || choice > 5);
		
		return choice;
	}
}