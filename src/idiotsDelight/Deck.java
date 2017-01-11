package idiotsDelight;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
	private ArrayList<Card> deck;
	
	public Deck()
	{
		deck = new ArrayList<Card>();
		for(int i = 0; i < 13; i++)
		{
			Rank rank = Rank.values()[i];
			for(int j = 0; j < 4; j++)
			{
				Color color = null;
				if(Suit.values()[j] == Suit.DIAMONDS || Suit.values()[j] == Suit.HEARTS)
					color = Color.RED;
				else
					color = Color.BLACK;
				deck.add(new Card(rank, Suit.values()[j], color));
			}
			
		}
	}
	
	public Card deal()
	{
		if(!isEmpty())
			return deck.remove(deck.size() - 1);
		else
			throw new EmptyDeckException();
	}
	
	public void shuffle()
	{
		Random rand = new Random();
		for(int i = 0; i < deck.size(); i++)
		{
			int num = rand.nextInt(52);
			Card holder = deck.get(i);
			deck.set(i, deck.get(num));
			deck.set(num, holder);
		}
	}
	
	public boolean isEmpty()
	{
		return deck.isEmpty();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < deck.size(); i++)
		{
			sb.append("\n" + deck.get(i).toString());
		}
		return sb.toString();
	}
}
