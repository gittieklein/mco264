package sortMerge;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;

public class SortMerge 
{
	private PrintWriter notSorted;
	private File[] tempFiles;
	private String unsortedFileName;
	private String sortedFileName;
	private Scanner readUnsortedData;
	private int randomIntegers;
	
	public SortMerge(String fileName, String sortedFileName, int num) throws IOException
	{
		notSorted = new PrintWriter(fileName);
		this.unsortedFileName = fileName;
		this.sortedFileName = sortedFileName;
		this.randomIntegers = num;
		generateNumbers();
	}
	
	/**
	 * generates the random numbers based on the amount the user wants and
	 * writes them to a file. then it takes the random numbers from that file and
	 * sorts the first 10 and writes those to a file and continues to do that until all
	 * numbers from the file are read and written to another file.
	 * @param randomIntegers the number of random numbers the user wants
	 * @throws IOException
	 */
	private void generateNumbers() throws IOException
	{		
		Random generator = new Random(System.currentTimeMillis());
		int value;
		
		//write random numbers to the file
		for(int i = 0; i < randomIntegers; i++)
		{
			value = generator.nextInt(500);	//200 is random - this way it's easy to see if the numbers are sorted
			notSorted.print(value + " ");
		}
		
		//close the file with unsorted numbers
		notSorted.close();
	}
	
	public void sort() throws FileNotFoundException
	{
		divideFiles();
		
		//now merge all the files
		PrintWriter sorted = new PrintWriter(sortedFileName);
		File temp = new File("temp.txt");
		PrintWriter tempwrite = new PrintWriter(temp);
		tempwrite.close();
		for(int i = 0; i < tempFiles.length; i++)
		{
			temp = merge(temp, tempFiles[i]);
		}
		Scanner readTemp = new Scanner(temp);
		readTemp.nextInt();	//get rid of size
		while (readTemp.hasNext()) 
		{
			sorted.print(readTemp.nextInt() + " ");
		}
		sorted.close();
		readTemp.close();
		
		deleteFiels();
	}
	
	private void divideFiles() throws FileNotFoundException
	{
		//you want to divided the numbers into multiple files
		//check how many files to divided the numbers into - you want at most 10 numbers per file
		int numTempFiles = (int) randomIntegers/10;
		
		//if num is not a multiple of 10, you need another file for the rest of the numbers
		if(randomIntegers % 10 > 0)
			numTempFiles++;
		
		//crate an array of files to hold the sorted sections
		tempFiles = new File[numTempFiles];
		for(int i = 0; i < numTempFiles; i++)
			tempFiles[i] = new File("Sorted" + (i + 1) + ".txt");
		
		//pass the file to a scanner so you could read the data from it
		readUnsortedData = new Scanner(new File(unsortedFileName));
		
		//create a temporary arrayList to hold values while you sort them,
		//so you could read it to a file once it's sorted
		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
		//put up to 10 numbers in each file
		int count = 0, fileNum = 0;
		//PrintWriter so you can write to file array
		PrintWriter pwForTempFileArray = new PrintWriter(tempFiles[fileNum]);
		while(readUnsortedData.hasNext())
		{
			//if you added 10 elements to the arraylist
			if(count == 10)
			{
				//sort the array list
				actualSort(tempArrayList, 0, tempArrayList.size() -1);
				//write the number of values
				pwForTempFileArray.println(count);
				//write the values to a file
				for(int i = 0; i < tempArrayList.size(); i++)
					pwForTempFileArray.println(tempArrayList.get(i));
				//close the file you just wrote the number to
				pwForTempFileArray.close();
				//increase the index so the next time you write to a file, you write to a new file
				fileNum++;
				//create a new print writer
				pwForTempFileArray = new PrintWriter(tempFiles[fileNum]);
				//set count to 0 because you will start to add to the arraylist from the beginning again
				count = 0;
				//clear the array list
				tempArrayList.clear();
			}
			//add numbers to the array list until there are 10 elements to sort
			tempArrayList.add(readUnsortedData.nextInt());
			count++;
		}
		//now you have to sort the elements that are left over
		//if elements were added after the last time the array list was sorted
		//sort the array list
		actualSort(tempArrayList, 0, tempArrayList.size() -1);	
		//write the values to a file
		pwForTempFileArray.println(count);
		for(int i = 0; i < tempArrayList.size(); i++)
			pwForTempFileArray.println(tempArrayList.get(i));
		
		//close the file you just wrote the number to
		pwForTempFileArray.close();
		readUnsortedData.close();
	}

	private void deleteFiels() 
	{
		//delete the files that were created as temporary files
		for(int i = 0; i < tempFiles.length; i++)
			tempFiles[i].delete();
	}
	
	/**
	 * sort the temporary arraylist
	 * @param tempList
	 * @param first
	 * @param last
	 */
	private void actualSort(ArrayList<Integer> tempList, int first, int last)
	{
		int pos;
		if(first < last)
		{
			//there is still data left
			pos = split(tempList, first, last);
			//sort left half of list
			actualSort(tempList, first, pos-1);
			//sort right half of list
			actualSort(tempList, pos+1, last);
		}
	}
	
	/**
	 * split the list and put in order
	 * @param tempList
	 * @param first
	 * @param last
	 * @return
	 */
	private int split(ArrayList<Integer> tempList, int first, int last)
	{
		int pos;
		int left = first;
		int right = last;
		
		//choose pivot value
		int pivot = tempList.get(left);
		int temp;
		
		while(left<right)
		{
			// while items remain in the list
			// search from right to find elements that are <= pivot and should
			// be swapped over to the set on the left
			
			//if the number on the right is greater than the pivot, then it's 
			//in the right place, so move left one place
			while(tempList.get(right) > pivot)
				right--;
			
			//now that you found an item on the right less than the pivot, search from the 
			//left for an integer that is greater than the pivot
			while(left<right && tempList.get(left) <= pivot)
				left++;
			
			if(left<right)
			{
				//you found 2 values that should be swapped
				temp = tempList.get(left);
				tempList.set(left, tempList.get(right));
				tempList.set(right, temp);
			}
		}
		//now put the pivot in the right place - between the 2 lists
		pos = right;
		tempList.set(first, tempList.get(pos));
		tempList.set(pos, pivot);
		return pos;
	}
	
	public File merge(File file1, File file2) throws FileNotFoundException  
	{
		Scanner read1 = new Scanner(file1);
		Scanner read2 = new Scanner(file2);
		
		int val1 = 0, val2 = 0;
		int size1 = -1, size2 = -1;
		int count1 = 0, count2 = 0;
		
		if(read1.hasNext())
		{
			size1 = read1.nextInt();
			if(read1.hasNext())
			{
				val1 = read1.nextInt();
				count1++;
			}
		}
		if(read2.hasNext())
		{
			size2 = read2.nextInt();
			if(read2.hasNext())
			{
				val2 = read2.nextInt();
				count2++;
			}
		}
		
		//create file/object for sorted data
		PrintWriter sorted = new PrintWriter("tempsorted.txt");
		
		int size;
		if (size1 == -1) {
			size = size2;
		} else {
			size = size1 + size2;
		}

		sorted.println(size);
		
		while(count1 <= size1 && count2 <= size2)
		{
			if(val1 <= val2)
			{
				sorted.println(val1);
				if(count1 < size1)
				{
					val1 = read1.nextInt();
				}
				count1++;
			}
			else
			{
				sorted.println(val2);
				if(count2 < size2)
				{
					val2 = read2.nextInt();
				}
				count2++;
			}
		}
		
		//if read1 has more, add them
		if(count1 <= size1)
		{
			while(count1 <= size1)
			{
				sorted.println(val1);
				if(count1 < size1)
				{
					val1 = read1.nextInt();
				}
				count1++;
			}
		}
		//if read2 has more, add them
		else
		{
			while(count2 <= size2)
			{
				sorted.println(val2);
				if(count2 < size2)
				{
					val2 = read2.nextInt();
				}
				count2++;
			}
		}
		read2.close();
		sorted.flush();
		sorted.close();
		return new File("tempsorted.txt");
	}
	
	
}
