
package stack;

import java.util.Scanner;

public class BinaryToDecimal 
{
	public static void main(String args[])
	{
		StackLinkedList<Integer> number = new StackLinkedList<Integer>();
		
		Scanner input = new Scanner(System.in);
		int dec, response = 2;
		
		do
		{
			System.out.print("Please enter the decimal number you would like to conver to binary: ");
			dec = input.nextInt();
			
			while(dec < 0)
			{
				System.out.println("You must enter a number that is zero or greater.");
				System.out.print("Please enter the decimal number you would like to conver to binary: ");
				dec = input.nextInt();
			}
			
			while(dec > 0)
			{
				number.push(dec % 2);
				dec = dec / 2;
			}
			System.out.print("Your number in binary: ");
			while(!number.isEmpty())
			{
				System.out.print(number.peek());
				number.pop();
			}
			
			System.out.print("\n\nWould you like to try another number? 1 = yes, 2 = no ");
			response = input.nextInt();
			System.out.println();
		}while(response == 1);
		
		System.out.println("Good Bye!");
		System.exit(0);
	}
}
