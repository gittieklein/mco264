package idiotsDelight;

import java.util.Stack;
import java.util.Scanner;

public class IdiotsDelight 
{
	private Deck theDeck;
	private Stack<Card>[] stacks;
	private int remaining;
		
	public IdiotsDelight()
	{
		theDeck = new Deck();
		theDeck.shuffle();
		stacks = new Stack[4];
		for(int i = 0; i < stacks.length; i++)
		{
			stacks[i] = new Stack<Card>();
		}
		
		deal();
		
		remaining = 52;
	}
	
	public void deal()
	{
		for(int i = 0; i < stacks.length; i++)
		{
			stacks[i].push(theDeck.deal());
		}
	}
	
	public String display()
	{
		StringBuilder sb = new StringBuilder();
		//this is the equivalent of toString
		for(int i = 0; i < stacks.length; i++)
			sb.append("Stack " + (i + 1) + ":\t");
		sb.append("\n");
		for(int i = 0; i < stacks.length; i++)
		{
			if(!stacks[i].isEmpty())
				sb.append(stacks[i].peek() + "\t\t");
			else
				sb.append("\t\t");
		}
		sb.append("There are " + remaining + " cards left.");
		return sb.toString();
	}
	
	public void discard(int card1, int card2)
	{
		//after the card is popped decrement the amount of cards that are left to remove
		if(stacks[card1].isEmpty() || stacks[card2].isEmpty())
			throw new EmptyDeckException();
		if(stacks[card1].peek().getRank().getValue() == stacks[card2].peek().getRank().getValue())
		{
			stacks[card1].pop();
			stacks[card2].pop();
			remaining -= 2;
		}
		else				
		{
			throw new NotValidMatchException();
		}	
	}
	
	public void discard(int card)
	{
		boolean flag = false;
		if(stacks[card].isEmpty())
			throw new EmptyDeckException();
		for(int i = 0; i < stacks.length && !flag; i++)
		{
			if(i == card || stacks[i].isEmpty())
				continue;
			if(stacks[card].peek().getSuit().toString().equals(stacks[i].peek().getSuit().toString()) 
					&& stacks[card].peek().getRank().getValue() < stacks[i].peek().getRank().getValue())
			{ 
				stacks[card].pop();
				remaining -= 1;
				flag = true;
			}
		}
		if(!flag)
			throw new NotValidMatchException();
	}
	
	public boolean gameWon()
	{
		return remaining == 0;
	}
	
	public static void main(String[] args)
	{
		IdiotsDelight game = new IdiotsDelight();
		Scanner input = new Scanner(System.in);
		int select;
		
		//game rules
		System.out.println("GAME RULES:"
				+ "\nObjective: Discard all the cards"
				+ "\nSetup: Shuffle a deck of cards, deal four cards face up in a row, forming four stacks"
				+ "\nPlay: Each turn you can do any of the following:"
				+ "\n\t\t-If there are two cards with the same rank showing, discard both of them"
				+ "\n\t\t-If there are two cards with the same suit showing, discard the one with the lower rank"
				+ "\n\t\t-Deal four new cards, one on the top of each stack\n\n\n");
		
		do 
		{
			System.out.println(game.display());
			do 
			{
				System.out.println("\nPlease select from the following menu:"
						+ "\n1. Deal cards"
						+ "\n2. Remove cards by rank"
						+ "\n3. Remove cards by suit"
						+ "\n4. Exit");
				select = input.nextInt();
			} while(select < 1 || select > 4);
			switch(select)
			{
				case 1:
					try
					{
						game.deal();
					}
					catch(EmptyDeckException e)
					{
						System.out.println(e);
					}
					break;
				case 2:
					int card1, card2;
					do 
					{
						System.out.print("Enter the two stacks that have the same rank: ");
						card1 = input.nextInt();
						card2 = input.nextInt();
					} while (card1 > 4 || card1 < 1 || card2 > 4 || card2 < 1 || card1 == card2);
					try
					{
						game.discard(card1 - 1, card2 - 1);
					}
					catch(NotValidMatchException e)
					{
						System.out.println(e);
					}
					catch(EmptyDeckException x)
					{
						System.out.println(x);
					}
					break;
				case 3:
					int card;
					do
					{
						System.out.print("Enter the stack with the lower rank: ");
						card = input.nextInt();
					} while(card > 4 || card < 1);
					try
					{
						game.discard(card - 1);
					}
					catch(NotValidMatchException e)
					{
						System.out.println(e);
					}
					catch(EmptyDeckException x)
					{
						System.out.println(x);
					}
					break;
				case 4:
					System.out.println("Thank you for playing:)");
					System.exit(0);
			}
		} while(!game.gameWon());
		System.out.println("YOU WIN!");
	}
}
