package recursion;

import java.io.File;
import java.util.Scanner;

public class ExploreFilesAndFolders 
{
	//set these to fields instead of local variables so they don't need to be passed as parameters
	public static File largestFile;
	public static StringBuilder allFiles;
	public static int quantity = 1;
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Which directory would you like to explore?");
		File directory = new File(input.nextLine());
		if(!(directory.isDirectory())) 
		{
			System.out.println("You must enter the name of a directory. "
					+ "\nPlease start the application again.");	
			System.exit(0);
		}
		largestFile = directory;	//set largest file to initial file and check based on that
		allFiles = new StringBuilder();
		
		allFiles.append(String.format("%s%n", directory.getName()));	//add the original folder to the string builder
		printFiles(directory); 
		
		System.out.println(allFiles);
		displayLargest(largestFile);
	}
	
	public static void printFiles(File directory)
	{
		if(directory.listFiles()==null) return;
		for(File f: directory.listFiles())
		{
			if(f.isFile())
			{
				tab(quantity);
				allFiles.append(String.format("%-45s %,10.1f KB%n", f.getName(), f.length()/1024.0));
				if(largestFile.length() < f.length())
				{
					largestFile = f;				
				}
			}
			else
			{
				tab(quantity);
				allFiles.append(String.format("%s%n", f.getName()));
				quantity++;
				printFiles(f);
				quantity--;
			}
		}
	}
	
	public static void displayLargest(File largestFile)
	{
		System.out.printf("%n%n%nThe Largest File:");
		System.out.printf("%n%-45s", largestFile.getName());
		System.out.printf("%,10.1f KB%n", largestFile.length()/1024.0);
	}
	
	public static void tab(int quantity)
	{
		if(quantity == 0)
			return;
		allFiles.append("\t");
		tab(quantity - 1);
	}
}
