package sortMerge;

import java.io.*;
import java.util.Scanner;

public class UseSortMerge 
{
	public static void main(String[] args)
	{
		String location = "NotSorted.txt";
		String sortedLocation = "SortedNumbers.txt";
		int num = 600;
		
		try
		{
			SortMerge list = new SortMerge(location, sortedLocation, num);
			Scanner numbers = new Scanner(new File(location));
			System.out.println("Unsorted Data:");
			while(numbers.hasNext())
				System.out.print(numbers.nextInt() + "   ");
			System.out.println();
			list.sort();
			Scanner numbers2 = new Scanner(new File(sortedLocation));
			System.out.println("Sorted Data:");
			while(numbers2.hasNext())
				System.out.print(numbers2.nextInt() + "   ");
			numbers.close();
			numbers2.close();

		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}
}
